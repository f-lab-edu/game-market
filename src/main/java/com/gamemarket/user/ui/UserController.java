package com.gamemarket.user.ui;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.application.UserService;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "sign-up")
    public void signUp(@RequestBody @Valid final UserSignUpRequest request) {
        if (userService.validateDuplicateEmail(request.getEmail())) {
            throw new UserException(UserExceptionCode.EXISTS_USER_EMAIL);
        }

        if (userService.validateDuplicateNickname(request.getNickname())) {
            throw new UserException(UserExceptionCode.EXISTS_USER_NICKNAME);
        }

        userService.signUp(request);
    }

}
