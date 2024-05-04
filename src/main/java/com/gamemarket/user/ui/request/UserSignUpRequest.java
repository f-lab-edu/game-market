package com.gamemarket.user.ui.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignUpRequest {

    private static final String EMAIL_REGEXP = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$";
    private static final String NICKNAME_REGEXP = "^[가-힣a-zA-Z]+$";
    private static final String PASSWORD_REGEXP = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{10,16}";

    @NotBlank(message = "이메일은 필수 입력 입니다.")
    @Pattern(regexp = EMAIL_REGEXP, message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 입니다.")
    @Size(max = 10, message = "닉네임은 10글자 이하이어야 합니다.")
    @Pattern(regexp = NICKNAME_REGEXP, message = "닉네임은 한글 또는 영어이어야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 입니다.")
    @Pattern(regexp = PASSWORD_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

}
