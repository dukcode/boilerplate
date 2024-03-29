package com.dukcode.boilerplate.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Password {

  private static final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

  private String encodedPassword;

  public Password(final String rawPassword) {
    this.encodedPassword = encodePassword(rawPassword);
  }

  private String encodePassword(final String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  public void changePassword(final String oldRawPassword, final String newRawPassword) {
    if (isMatches(oldRawPassword)) {
      this.encodedPassword = encodePassword(newRawPassword);
    }
  }

  public boolean isMatches(String rawPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }
}
