package com.example.demo;

public class NumberedMessage {

  private int number;
  private String timestamp;

  public NumberedMessage(int number, String timestamp) {
    this.number = number;
    this.timestamp = timestamp;
  }

  public int getNumber() {
    return number;
  }

  public String getTimestamp() {
    return timestamp;
  }
}
