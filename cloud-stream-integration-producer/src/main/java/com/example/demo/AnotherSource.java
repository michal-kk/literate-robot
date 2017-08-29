package com.example.demo;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface AnotherSource {

  String ANOTHER_OUTPUT = "another-output";

  @Output(AnotherSource.ANOTHER_OUTPUT)
  MessageChannel output();
}
