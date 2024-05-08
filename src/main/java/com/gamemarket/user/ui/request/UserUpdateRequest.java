package com.gamemarket.user.ui.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

import static com.gamemarket.user.utils.UserConst.NICKNAME_REGEXP;
import static com.gamemarket.user.utils.UserConst.PASSWORD_REGEXP;

@Getter
@ToString
public class UserUpdateRequest {

    @Size(max = 10, message = "닉네임은 10글자 이하이어야 합니다.")
    @Pattern(regexp = NICKNAME_REGEXP, message = "닉네임은 한글 또는 영어이어야 합니다.")
    private String nickname;

    @Pattern(regexp = PASSWORD_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

}
