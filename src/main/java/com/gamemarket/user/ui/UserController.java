package com.gamemarket.user.ui;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.common.utils.SessionUtil;
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
import org.apache.commons.lang3.StringUtils;
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
    @Operation(summary = "sign-up")
    public void signUp(@RequestBody @Valid final UserSignUpRequest request) {
       existsByEmail(request.getEmail());
       existsByNickname(request.getNickname());

       userService.signUp(request);
    }

    @PatchMapping("/sign-off")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "sign-off")
    public void signOff(@RequestBody @Valid final UserSignOffRequest request, final HttpSession session) {
        final User user = SessionUtil.getUserFromSession(session);
        userService.signOff(user, request);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "sign-in")
    public void signIn(@RequestBody @Valid final UserSignInRequest request, final HttpSession session) {
        final User user = userService.signIn(request);
        session.setAttribute(SESSION_USER_KEY, user);
    }

    @PostMapping("/sign-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "sign-out")
    public void signOut(final HttpSession session) {
        session.invalidate();
    }

    @PatchMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "user profile update")
    public void profileUpdate(@RequestBody @Valid final UserUpdateRequest request, final HttpSession session) {
        final User user = SessionUtil.getUserFromSession(session);

        if (!StringUtils.isEmpty(request.getNickname())) {
            existsByNickname(request.getNickname());
        }

        userService.profileUpdate(user, request);
    }

    private void existsByEmail(final String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserException(UserExceptionCode.EXISTS_USER_EMAIL);
        }
    }

    private void existsByNickname(final String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw new UserException(UserExceptionCode.EXISTS_USER_NICKNAME);
        }
    }

}
