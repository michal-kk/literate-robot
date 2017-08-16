package com.example.demo;

import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "example.integrationproducer")
public class IntegrationProducerProperties {

  @Min(500)
  private int pollerDelay;

  public int getPollerDelay() {
    return pollerDelay;
  }

  public void setPollerDelay(int pollerDelay) {
    this.pollerDelay = pollerDelay;
  }
}
