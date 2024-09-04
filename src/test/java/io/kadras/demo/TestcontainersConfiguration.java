package io.kadras.demo;

import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.grafana.LgtmStackContainer;

import org.testcontainers.utility.DockerImageName;

import java.time.Duration;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	

	
	@Bean
	@RestartScope
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("pgvector/pgvector:pg16"));
	}

	@Bean
	@RestartScope
	@ServiceConnection
	LgtmStackContainer lgtmContainer() {
		return new LgtmStackContainer("grafana/otel-lgtm:0.7.1")
				.withStartupTimeout(Duration.ofMinutes(2))
				.withReuse(true);
	}

}
