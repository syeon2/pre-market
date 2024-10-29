package io.syeony.premarket.item.infrastructure.kafka;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.syeony.premarket.item.infrastructure.kafka.configuration.KafkaItemStockConst;
import io.syeony.premarket.item.infrastructure.persistence.ItemRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

	private final ItemRepository itemRepository;

	@Transactional
	@KafkaListener(topics = KafkaItemStockConst.ITEM_STOCK_TOPICS)
	public void updateQty(String message) {
		Map<Object, Object> map = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			map = mapper.readValue(message, new TypeReference<HashMap<Object, Object>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		long itemNo = Long.parseLong(String.valueOf(map.get(ItemStock.ITEM_NO_FIELD)));
		int quantity = Integer.parseInt(String.valueOf(map.get(ItemStock.QUANTITY_FIELD)));

		itemRepository.findById(itemNo).ifPresent(item -> {
			item.deductStock(quantity);
		});
	}
}
