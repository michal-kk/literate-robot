package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CloudStreamMessageProducerApplicationTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private MessageCollector messageCollector;

  @Autowired
  private Source source;

  @SuppressWarnings("unchecked")
  @Test
  public void testGetRequest() {
    String responseBody = restTemplate.getForObject("/", String.class);
    assertThat(responseBody).isEqualTo("ok");

    Message<String> message = (Message<String>) messageCollector.forChannel(source.output()).poll();
    assertThat(message.getPayload()).contains("{\"number\":1");

    responseBody = restTemplate.getForObject("/", String.class);
    assertThat(responseBody).isEqualTo("ok");

    message = (Message<String>) messageCollector.forChannel(source.output()).poll();
    assertThat(message.getPayload()).contains("{\"number\":2");
  }
}
