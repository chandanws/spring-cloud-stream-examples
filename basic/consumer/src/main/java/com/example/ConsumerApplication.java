package com.example;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@EnableBinding(Sink.class)
@SpringBootApplication
public class ConsumerApplication {
	private Logger logger = Logger.getLogger(ConsumerApplication.class);
	
	@StreamListener(Sink.INPUT)
	public void log(Message<String> m){
		logger.info(m.getPayload());
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}
