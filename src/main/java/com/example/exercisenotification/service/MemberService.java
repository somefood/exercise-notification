package com.example.exercisenotification.service;

import com.example.exercisenotification.common.Message;
import com.example.exercisenotification.dto.MemberDTO;
import com.example.exercisenotification.entity.Member;
import com.example.exercisenotification.entity.MemberRole;
import com.example.exercisenotification.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberDTO getMember(String username) {
        Optional<Member> result = memberRepository.findByUsername(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException(Message.USER_NOT_FOUND.getMessage());
        }

        Member member = result.get();
        return entityToDTO(member);
    }

    @Transactional
    public Long register(MemberDTO memberDTO) {
        checkUnique(memberDTO.getUsername());
        Member member = dtoToEntity(memberDTO);
        member.addMemberRole(MemberRole.USER);
        memberRepository.save(member);
        return member.getId();
    }

    private void checkUnique(String username) {
        Optional<Member> result = memberRepository.findByUsername(username);
        if (result.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
    }

    public MemberDTO entityToDTO(Member member) {
        return MemberDTO.builder()
                .id(member.getId())
                .username(member.getUsername())
                .password(member.getPassword())
                .build();
    }

    public Member dtoToEntity(MemberDTO memberDTO) {
        return Member.builder()
                .username(memberDTO.getUsername())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .build();
    }
}
