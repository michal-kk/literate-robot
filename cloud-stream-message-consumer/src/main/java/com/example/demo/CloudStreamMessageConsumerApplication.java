package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class CloudStreamMessageConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CloudStreamMessageConsumerApplication.class, args);
  }

  @StreamListener(Sink.INPUT)
  public void handleMessage(byte[] message) {
    System.out.println("Message is: " + new String(message));
  }
}
