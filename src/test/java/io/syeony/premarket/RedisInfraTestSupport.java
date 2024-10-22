package io.syeony.premarket;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.test.context.ActiveProfiles;

@DataRedisTest
@ActiveProfiles(value = "test")
@ExtendWith(TestContainerConfiguration.class)
public abstract class RedisInfraTestSupport {
}
