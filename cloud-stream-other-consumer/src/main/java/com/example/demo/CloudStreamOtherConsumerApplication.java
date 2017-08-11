package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CloudStreamOtherConsumerApplication {

  private static final Logger logger =
      LoggerFactory.getLogger(CloudStreamOtherConsumerApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(CloudStreamOtherConsumerApplication.class, args);
  }

  @StreamListener(Sink.INPUT)
  public void handleMessage(String message) {
    logger.info("Upper case message is: {}", message.toUpperCase());
  }
}
