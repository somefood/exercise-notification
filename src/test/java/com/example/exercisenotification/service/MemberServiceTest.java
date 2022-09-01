package com.example.exercisenotification.service;

import com.example.exercisenotification.dto.MemberDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void registerTest() {
        MemberDTO memberDTO = MemberDTO.builder().username("seokju").password("석주12").build();
        Long id = memberService.register(memberDTO);

        Assertions.assertThat(id).isEqualTo(1L);
    }
}