package com.example.demo;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @RequestMapping
  public ResponseEntity<NumberedMessage> sendMessage() {
    final NumberedMessage numberedMessage =
        new NumberedMessage(counter.incrementAndGet(), Instant.now().toString());

    source.output().send(MessageBuilder.withPayload(numberedMessage).build());

    return ResponseEntity.ok(numberedMessage);
  }
}
