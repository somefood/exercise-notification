package com.example.exercisenotification.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class MemberDTO {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Builder
    public MemberDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
