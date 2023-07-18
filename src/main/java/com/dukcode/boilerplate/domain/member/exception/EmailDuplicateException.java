package com.dukcode.boilerplate.domain.member.exception;

import com.dukcode.boilerplate.global.error.exception.DuplicateException;
import com.dukcode.boilerplate.global.error.exception.ErrorCode;

public class EmailDuplicateException extends DuplicateException {

  public EmailDuplicateException(final String email) {
    super(email, ErrorCode.EMAIL_DUPLICATE);
  }
}
