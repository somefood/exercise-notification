package com.example.exercisenotification.common;

import lombok.Getter;

@Getter
public enum Message {
    USER_NOT_FOUND("유저를 찾을 수 없습니다.");

    private final String message;

    Message(String message) {
        this.message = message;
    }
}
