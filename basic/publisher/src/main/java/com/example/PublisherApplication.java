package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
@SpringBootApplication
public class PublisherApplication {
	
	private MessageChannel consumer;
	
	public PublisherApplication(Source s){
		this.consumer = s.output();
	}

	@PostMapping(value="/greeting/{name}")
	public void publish(@PathVariable String name){
		String greeting = "Hello " + name;
		Message<String> m = MessageBuilder.withPayload(greeting).build();
		this.consumer.send(m); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(PublisherApplication.class, args);
	}
}

//interface ProducerChannels {
//	@Output
//	MessageChannel consumer();
//}
