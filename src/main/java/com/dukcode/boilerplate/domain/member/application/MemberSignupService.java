package com.dukcode.boilerplate.domain.member.application;

import com.dukcode.boilerplate.domain.member.Member;
import com.dukcode.boilerplate.domain.member.dto.request.MemberSignupRequest;
import com.dukcode.boilerplate.domain.member.dto.response.MemberSignupResponse;
import com.dukcode.boilerplate.domain.member.exception.EmailDuplicateException;
import com.dukcode.boilerplate.domain.member.exception.NicknameDuplicateException;
import com.dukcode.boilerplate.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberSignupService {

  private final MemberRepository memberRepository;

  @Transactional
  public MemberSignupResponse createMember(MemberSignupRequest request) {
    if (memberRepository.existsByEmail(request.getEmail())) {
      throw new EmailDuplicateException(request.getEmail());
    }

    if (memberRepository.existsByNickname(request.getNickname())) {
      throw new NicknameDuplicateException(request.getNickname());
    }

    Member member = request.toMember();
    memberRepository.save(member);

    return new MemberSignupResponse(member);
  }

}
