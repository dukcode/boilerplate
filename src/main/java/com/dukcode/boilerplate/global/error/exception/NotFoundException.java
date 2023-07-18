package com.dukcode.boilerplate.global.error.exception;

public class NotFoundException extends BusinessException {

  private String value;

  public NotFoundException(String value) {
    this(value, ErrorCode.DUPLICATE);
    this.value = value;
  }

  public NotFoundException(String value, ErrorCode errorCode) {
    super(value, errorCode);
    this.value = value;
  }
}