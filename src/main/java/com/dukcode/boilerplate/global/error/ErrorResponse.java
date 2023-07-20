package com.dukcode.boilerplate.global.error;

import com.dukcode.boilerplate.global.error.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

  private int status;
  private String code;
  private String message;
  private List<String> values = new ArrayList<>();

  private ErrorResponse(final int status, final ErrorCode code) {
    this.status = status;
    this.message = code.getMessage();
    this.code = code.getCode();
  }

  private ErrorResponse(final int status, final ErrorCode code, final String value) {
    this(status, code);
    this.values = List.of(value);
  }

  private ErrorResponse(final int status, final ErrorCode code, final List<String> values) {
    this(status, code);
    this.values = values;
  }

  public static ErrorResponse of(final int status, final ErrorCode code,
      final BindingResult bindingResult) {

    List<String> values = bindingResult.getFieldErrors().stream()
        .map(error ->
            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString())
        .collect(Collectors.toList());
    return new ErrorResponse(status, code, values);
  }

  public static ErrorResponse of(final int status, final ErrorCode code) {
    return new ErrorResponse(status, code);
  }

  public static ErrorResponse of(final int status, final ErrorCode code,
      final String value) {
    return new ErrorResponse(status, code, value);
  }

  public static ErrorResponse of(int status, MethodArgumentTypeMismatchException e) {
    final String value = e.getValue() == null ? "" : e.getValue().toString();
    return new ErrorResponse(status, ErrorCode.INVALID_TYPE_VALUE, value);
  }

}
