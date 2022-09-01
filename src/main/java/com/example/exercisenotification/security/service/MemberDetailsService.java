package com.example.exercisenotification.security.service;

import com.example.exercisenotification.entity.Member;
import com.example.exercisenotification.repository.MemberRepository;
import com.example.exercisenotification.security.dto.MemberAuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByUsername(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        }

        Member member = result.get();

        MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
                member.getUsername(),
                member.getPassword(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList())
        );

        memberAuthDTO.setUsername(member.getUsername());

        return memberAuthDTO;
    }
}
