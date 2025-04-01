package com.lopez.user_service;

import javax.swing.SortOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.web.client.RestTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	MongoClient mongoClient() {
		return MongoClients.create("mongodb://127.0.0.1:27017/");
	}

	@Bean
	MongoOperations mongoTemplate(MongoClient mongoClient) {
		MongoTemplate template = new MongoTemplate(mongoClient, "users");
		template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
		template.indexOps("users").ensureIndex(new Index("idNumber", Direction.ASC).unique());
		return template;
	}

}
