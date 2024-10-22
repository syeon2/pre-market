package io.syeony.premarket;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

public class TestContainerConfiguration implements BeforeAllCallback, AfterAllCallback {

	private static final String REDIS_IMAGE = "redis:latest";
	private static final Integer REDIS_PORT = 6379;

	private GenericContainer<?> redisContainer;

	@Override
	public void beforeAll(ExtensionContext extensionContext) {
		redisContainer = new GenericContainer<>(REDIS_IMAGE)
			.withExposedPorts(REDIS_PORT);

		redisContainer.start();

		System.setProperty("spring.data.redis.host", redisContainer.getHost());
		System.setProperty("spring.data.redis.port", redisContainer.getMappedPort(REDIS_PORT).toString());
	}

	@Override
	public void afterAll(ExtensionContext extensionContext) throws Exception {
		redisContainer.stop();
	}

}
