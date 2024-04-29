package com.gamemarket.user.ui;

import com.gamemarket.user.application.UserService;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "sign-up")
    public void signUp(@RequestBody @Valid final UserSignUpRequest request) {
        userService.signUp(request);
    }

}
