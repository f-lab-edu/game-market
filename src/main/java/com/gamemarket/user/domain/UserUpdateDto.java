package com.gamemarket.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateDto {

    private final Long id;
    private final String nickname;
    private final String password;

    @Builder
    public UserUpdateDto(final Long id, final String nickname, final String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

}
