package com.example.demo;


import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
public class CloudStreamMessageProducerApplication {

  private static AtomicInteger counter = new AtomicInteger(0);

  @Autowired
  private Source source;

  public static void main(String[] args) {
    SpringApplication.run(CloudStreamMessageProducerApplication.class, args);
  }

  @GetMapping
  public String sendMessage() {
    source.output().send(MessageBuilder
        .withPayload("this is a message number " + counter.incrementAndGet()).build());

    return "ok";
  }
}
