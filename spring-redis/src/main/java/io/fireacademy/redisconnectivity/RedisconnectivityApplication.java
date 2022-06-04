package io.fireacademy.redisconnectivity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisconnectivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisconnectivityApplication.class, args);
	}
}
