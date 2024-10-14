package io.syeony.premarket;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@DataRedisTest
@EnableJpaAuditing
@ActiveProfiles(value = "test")
@ExtendWith(TestContainerConfiguration.class)
public abstract class InfrastructureTestSupport {
}
