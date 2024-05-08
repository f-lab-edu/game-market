package com.gamemarket.user.ui.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

import static com.gamemarket.user.utils.UserConst.PASSWORD_REGEXP;

@Getter
@ToString
public class UserSignOffRequest {

    @NotBlank(message = "비밀번호는 필수 입력 입니다.")
    @Pattern(regexp = PASSWORD_REGEXP, message = "비밀번호는 10~16자 영문 대 소문자, 숫자, 특수문자 형식이어야 합니다.")
    private String password;

}
