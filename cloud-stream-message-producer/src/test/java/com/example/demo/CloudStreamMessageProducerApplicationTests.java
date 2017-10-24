package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
  public void testGetRequest() throws JsonParseException, JsonMappingException, IOException {
    String responseBody = restTemplate.getForObject("/", String.class);
    assertThat(responseBody).isEqualTo("ok");

    Message<String> message = (Message<String>) messageCollector.forChannel(source.output()).poll();
    assertThat(jsonToTestedMessage(message.getPayload()).getNumber()).isEqualTo(1);

    responseBody = restTemplate.getForObject("/", String.class);
    assertThat(responseBody).isEqualTo("ok");

    message = (Message<String>) messageCollector.forChannel(source.output()).poll();
    assertThat(jsonToTestedMessage(message.getPayload()).getNumber()).isEqualTo(2);
  }

  private TestedMessage jsonToTestedMessage(final String jsonMessage)
      throws JsonParseException, JsonMappingException, IOException {
    final ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(jsonMessage, TestedMessage.class);
  }


  @JsonIgnoreProperties(value = {"timestamp"})
  private static class TestedMessage {
    private int number;

    public int getNumber() {
      return number;
    }
  }
}
