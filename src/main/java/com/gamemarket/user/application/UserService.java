package com.gamemarket.user.application;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.infra.UserRepository;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public void signUp(final UserSignUpRequest request) {
        validateDuplicateUser(request);

        final User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    private void validateDuplicateUser(final UserSignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserExceptionCode.EXISTS_USER_EMAIL);
        }

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new UserException(UserExceptionCode.EXISTS_USER_NICKNAME);
        }
    }

}
