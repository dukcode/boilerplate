package com.dukcode.boilerplate.global.error;

import com.dukcode.boilerplate.global.error.exception.BusinessException;
import com.dukcode.boilerplate.global.error.exception.DuplicateException;
import com.dukcode.boilerplate.global.error.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Validated 시 바인딩 에러가 존재할 때 발생
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException e) {
    log.info("Handle MethodArgumentNotValidException", e);

    final int status = HttpStatus.BAD_REQUEST.value();

    final ErrorResponse response = ErrorResponse.of(status, ErrorCode.INVALID_INPUT_VALUE,
        e.getBindingResult());

    return new ResponseEntity<>(response, HttpStatus.valueOf(status));
  }

  /**
   * RequestParam 타입이 맞지 않을 때 발생
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
      MethodArgumentTypeMismatchException e) {
    log.info("Handle MethodArgumentTypeMismatchException", e);

    final int status = HttpStatus.BAD_REQUEST.value();

    final ErrorResponse response = ErrorResponse.of(status, e);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  /**
   * 지원하지 않는 HTTP 메서드로 요청 시
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(
      HttpRequestMethodNotSupportedException e) {
    log.info("Handle HttpRequestMethodNotSupportedException", e);

    final int status = HttpStatus.METHOD_NOT_ALLOWED.value();

    final ErrorResponse response = ErrorResponse.of(status, ErrorCode.METHOD_NOT_ALLOWED);

    return new ResponseEntity<>(response, HttpStatus.valueOf(status));
  }

  /**
   * JSON Request Body 에서 type mismatch 가 발생할 때
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    log.info("Handle HttpMessageNotReadableException", e);

    final int status = HttpStatus.BAD_REQUEST.value();

    final ErrorResponse response = ErrorResponse.of(status, ErrorCode.INVALID_TYPE_VALUE);

    return new ResponseEntity<>(response,
        HttpStatus.valueOf(status));
  }

  @ExceptionHandler(DuplicateException.class)
  protected ResponseEntity<ErrorResponse> handleDuplicationException(final DuplicateException e) {
    log.info("Handle DuplicationException", e);

    final ErrorCode errorCode = e.getErrorCode();
    final String value = e.getValue();
    final int status = HttpStatus.CONFLICT.value();

    final ErrorResponse errorResponse = ErrorResponse.of(status, errorCode, value);

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
  }

  @ExceptionHandler(BusinessException.class)
  protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
    log.info("Handle BusinessException", e);

    final ErrorCode errorCode = e.getErrorCode();
    final int status = HttpStatus.BAD_GATEWAY.value();

    final ErrorResponse errorResponse = ErrorResponse.of(status, errorCode);

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(status));
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ErrorResponse> handleException(Exception e) {
    log.error("Handle Exception", e);

    final int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    final ErrorResponse response = ErrorResponse.of(status, ErrorCode.INTERNAL_SERVER_ERROR);

    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
