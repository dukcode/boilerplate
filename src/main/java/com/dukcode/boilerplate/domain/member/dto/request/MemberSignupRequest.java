package com.dukcode.boilerplate.domain.member.dto.request;

import com.dukcode.boilerplate.domain.member.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignupRequest {

  @Email
  private String email;

  @NotBlank
  private String password;

  @NotBlank
  private String name;

  @NotBlank
  private String nickname;

  @URL
  private String profileImageUrl;

  public Member toMember() {
    return Member.builder()
        .email(this.email)
        .rawPassword(password)
        .name(name)
        .nickname(nickname)
        .profileImageUrl(profileImageUrl)
        .build();
  }
}
