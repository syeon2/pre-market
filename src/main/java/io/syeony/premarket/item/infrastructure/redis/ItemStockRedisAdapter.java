package io.syeony.premarket.item.infrastructure.redis;

import java.util.Collections;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Repository;

import io.syeony.premarket.item.domain.ItemStockHandler;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemStockRedisAdapter implements ItemStockHandler {

	private final RedisTemplate<String, String> redisTemplate;

	private static final String PREFIX = "item:id:";
	private static final String DECREMENT_STOCK_SCRIPT =
		"local currentStock = tonumber(redis.call('get', KEYS[1])) "
			+ "local orderQuantity = tonumber(ARGV[1]) "
			+ "if currentStock == nil then return -1 "
			+ "end "
			+ "if currentStock < orderQuantity then return 0 "
			+ "end "
			+ "redis.call('decrby', KEYS[1], orderQuantity) "
			+ "return currentStock - orderQuantity ";

	@Override
	public void increaseStock(Long itemNo, Integer quantity) {
		redisTemplate.opsForValue().set(
			generatedKey(itemNo),
			quantity.toString()
		);
	}

	@Override
	public void deductStock(Long itemNo, Integer quantity) {
		DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
		redisScript.setScriptText(DECREMENT_STOCK_SCRIPT);
		redisScript.setResultType(Long.class);

		Long result = redisTemplate.execute(
			redisScript,
			Collections.singletonList(generatedKey(itemNo)),
			String.valueOf(quantity));

		if (result == null) {
			throw new RuntimeException("redis execution error");
		} else if (result == -1) {
			throw new IllegalArgumentException("상품 정보가 존재하지 않습니다.");
		} else if (result == 0) {
			throw new IllegalArgumentException("재고가 부족합니다.");
		}
	}

	private String generatedKey(Long itemId) {
		return PREFIX + itemId;
	}

}
