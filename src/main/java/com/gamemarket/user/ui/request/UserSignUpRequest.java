package com.gamemarket.user.ui.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.gamemarket.user.utils.UserConst.*;

@Getter
@Setter
@ToString
public class UserSignUpRequest {

    @NotBlank(message = "이메일은 필수 입력 입니다.")
    @Pattern(regexp = USER_EMAIL_REGEXP, message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "닉네임은 필수 입력 입니다.")
    @Size(max = 10, message = "닉네임은 10글자 이하이어야 합니다.")
    @Pattern(regexp = USER_NICKNAME_REGEXP, message = "닉네임은 한글 또는 영어이어야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수 입력 입니다.")
    @Pattern(regexp = USER_PASSWORD_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

}
