package com.dukcode.boilerplate.domain.member.exception;

import com.dukcode.boilerplate.global.error.exception.DuplicateException;
import com.dukcode.boilerplate.global.error.exception.ErrorCode;

public class NicknameDuplicateException extends DuplicateException {

  public NicknameDuplicateException(final String nickname) {
    super(nickname, ErrorCode.NICKNAME_DUPLICATE);
  }
}
