package com.example.exercisenotification.repository;

import com.example.exercisenotification.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createUser() {
        Member member = Member.builder().username("seokju").password("1234").build();
        Member savedMember = memberRepository.save(member);

        assertThat(savedMember.getUsername()).isEqualTo(member.getUsername());
    }
}