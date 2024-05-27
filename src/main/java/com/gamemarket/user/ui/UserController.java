package com.gamemarket.user.ui;

import com.gamemarket.user.domain.CurrentUser;
import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.application.UserService;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.infra.UserRepository;
import com.gamemarket.user.ui.request.UserSignInRequest;
import com.gamemarket.user.ui.request.UserSignOffRequest;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import com.gamemarket.user.ui.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.gamemarket.user.utils.UserConst.SESSION_USER_KEY;

@RestController
@RequiredArgsConstructor
@Tag(name = "User")
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입")
    public void signUp(@RequestBody @Valid final UserSignUpRequest request) {
        userRepository.existsByEmail(request.getEmail());
        userRepository.existsByNickname(request.getNickname());
        userService.signUp(request);
    }

    @PatchMapping("/sign-off")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "회원탈퇴")
    public void signOff(
            @RequestBody @Valid final UserSignOffRequest request,
            @CurrentUser User user,
            final HttpSession session
    ) {
        userService.signOff(user, request);
        session.removeAttribute(SESSION_USER_KEY);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그인")
    public void signIn(@RequestBody @Valid final UserSignInRequest request, final HttpSession session) {
        final User user = userService.signIn(request);
        session.setAttribute(SESSION_USER_KEY, user);
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "로그아웃")
    public void signOut(final HttpSession session) {
        session.invalidate();
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "회원정보변경")
    public void profileUpdate(@RequestBody @Valid final UserUpdateRequest request, @CurrentUser User user) {
        userRepository.existsByUpdateNickname(user.getId(), request.getNickname());
        userService.updateProfile(user, request);
    }

}
