package com.example.demo;

public class SimpleMessage {
  private int number;
  private String extraInfo;

  public SimpleMessage(int number, String extraInfo) {
    this.number = number;
    this.extraInfo = extraInfo;
  }

  public int getNumber() {
    return number;
  }

  public String getExtraInfo() {
    return extraInfo;
  }
}
