package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

@SpringBootApplication
@EnableBinding(Source.class)
public class CloudStreamIntegrationProducerApplication {

  private static AtomicInteger atomicInteger = new AtomicInteger(0);

  public static void main(String[] args) {
    SpringApplication.run(CloudStreamIntegrationProducerApplication.class, args);
  }

  @Bean
  @InboundChannelAdapter(value = Source.OUTPUT,
      poller = @Poller(fixedDelay = "${example.integrationproducer.pollerDelay}"))
  public MessageSource<SimpleMessage> timerMessageSource() {
    return () -> new GenericMessage<>(
        new SimpleMessage(atomicInteger.incrementAndGet(), "from channel adapter"));
  }
}
