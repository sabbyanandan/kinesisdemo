package com.example.kinesisdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.UUID;

@EnableScheduling
@EnableBinding(Source.class)
@SpringBootApplication
public class KinesisProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KinesisProducerApplication.class, args);
	}

	@Autowired
	private Source source;

	@Scheduled(fixedRate = 20000L)
	public String sendMessage() {
		UUID id = UUID.randomUUID();
		System.out.println("Before sending data: " + id);
		source.output().send(MessageBuilder.withPayload("Hello: " + id).build());
		System.out.println("After sending data: " + id);
		return "ok, have fun with payload!";
	}
}
