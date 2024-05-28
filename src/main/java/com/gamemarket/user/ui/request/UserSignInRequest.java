package com.gamemarket.user.ui.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class UserSignInRequest {

    @NotBlank(message = "이메일은 필수 입력 입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 입니다.")
    private String password;

    @NotNull(message = "회원상태는 필수 입력 입니다.")
    private Boolean status;

}
