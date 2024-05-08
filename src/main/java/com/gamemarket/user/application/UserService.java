package com.gamemarket.user.application;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.domain.UserUpdateDto;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.infra.UserRepository;
import com.gamemarket.user.ui.request.UserSignInRequest;
import com.gamemarket.user.ui.request.UserSignOffRequest;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import com.gamemarket.user.ui.request.UserUpdateRequest;
import io.micrometer.common.util.StringUtils;
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
        final User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }
    
    @Transactional
    public void signOff(final User user, final UserSignOffRequest request) {
        verifyPassword(request.getPassword(), user);
        userRepository.signOff(user);
    }

    @Transactional(readOnly = true)
    public User signIn(final UserSignInRequest request) {
        final User user = userRepository.findByEmail(request.getEmail());
        verifyPassword(request.getPassword(), user);

        return user;
    }

    @Transactional
    public void profileUpdate(final User user, final UserUpdateRequest request) {
        if (!StringUtils.isEmpty(request.getPassword())) {
            updateProfileNotEmptyPassword(user, request);
        } else {
            updateProfileEmptyPassword(user, request);
        }
    }

    private void verifyPassword(final String requestPassword, final User user) {
        if (!passwordEncoder.matches(requestPassword, user.getPassword())) {
            throw new UserException(UserExceptionCode.INVALID_CREDENTIALS);
        }
    }
    
    private void updateProfileNotEmptyPassword(final User user, final UserUpdateRequest request) {
        final UserUpdateDto dto = UserUpdateDto.builder()
                .id(user.getId())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.profileUpdate(dto);
    }

    private void updateProfileEmptyPassword(final User user, final UserUpdateRequest request) {
        final UserUpdateDto dto = UserUpdateDto.builder()
                .id(user.getId())
                .nickname(request.getNickname())
                .password(request.getPassword())
                .build();

        userRepository.profileUpdate(dto);
    }

}
