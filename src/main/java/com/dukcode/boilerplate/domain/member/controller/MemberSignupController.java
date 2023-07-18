package com.dukcode.boilerplate.domain.member.controller;

import com.dukcode.boilerplate.domain.member.application.MemberSignupService;
import com.dukcode.boilerplate.domain.member.dto.request.MemberSignupRequest;
import com.dukcode.boilerplate.domain.member.dto.response.MemberSignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberSignupController {

  private final MemberSignupService memberSignupService;

  @PostMapping("member/signup")
  public MemberSignupResponse signup(@Validated @RequestBody MemberSignupRequest request) {
    return memberSignupService.createMember(request);
  }

}
