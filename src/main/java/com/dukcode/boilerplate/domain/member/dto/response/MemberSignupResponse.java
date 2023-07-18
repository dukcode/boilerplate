package com.dukcode.boilerplate.domain.member.dto.response;

import com.dukcode.boilerplate.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignupResponse {

  private String email;
  private String nickname;

  public MemberSignupResponse(Member member) {
    email = member.getEmail();
    nickname = member.getNickname();
  }
}
