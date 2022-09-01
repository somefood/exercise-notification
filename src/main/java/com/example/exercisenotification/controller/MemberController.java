package com.example.exercisenotification.controller;

import com.example.exercisenotification.dto.MemberDTO;
import com.example.exercisenotification.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public String getMember(Model model) {
        log.info("get");
        model.addAttribute("member", new MemberDTO());
        return "user/register";
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Long> createMember(MemberDTO memberDTO) {
        log.info("createMember: {}", memberDTO);
        Long registeredId = memberService.register(memberDTO);
        return new ResponseEntity<>(registeredId, HttpStatus.OK);
    }
}
