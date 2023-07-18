package com.dukcode.boilerplate.domain.member;

import com.dukcode.boilerplate.domain.model.Password;
import com.dukcode.boilerplate.domain.model.Role;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  @AttributeOverride(name = "rawPassword", column = @Column(name = "password", nullable = false))
  @Embedded
  private Password password;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String nickname;

  private String profileImageUrl;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role = Role.ROLE_GUEST;

  @Builder
  public Member(String email, String rawPassword, String name, String nickname,
      String profileImageUrl) {
    this.email = email;
    this.password = new Password(rawPassword);
    this.name = name;
    this.nickname = nickname;
    this.profileImageUrl = profileImageUrl;
  }
}
