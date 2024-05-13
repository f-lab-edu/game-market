package com.gamemarket.user.application;

import com.gamemarket.common.exception.user.UserException;
import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.infra.UserMapper;
import com.gamemarket.user.infra.UserRepository;
import com.gamemarket.user.ui.request.UserSignInRequest;
import com.gamemarket.user.ui.request.UserSignOffRequest;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import com.gamemarket.user.ui.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void signUp(final UserSignUpRequest request) {
        final String encryptPassword = passwordEncoder.encode(request.getPassword());
        final User user = userMapper.signUp(request, encryptPassword);

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

    private void verifyPassword(final String requestPassword, final User user) {
        if (!passwordEncoder.matches(requestPassword, user.getPassword())) {
            throw new UserException(UserExceptionCode.INVALID_CREDENTIALS);
        }
    }

    @Transactional
    public void updateProfile(final User user, final UserUpdateRequest request) {
        final String nickname = getUpdateNickname(user, request);
        final String password = getUpdatePassword(user, request);
        final User userUpdate = userMapper.updateProfile(user.getId(), nickname, password);

        userRepository.profileUpdate(userUpdate);
    }

    public String getUpdateNickname(final User user, final UserUpdateRequest request) {
        if (request.isNicknameUpdate()) {
            return request.getNickname();
        }
        return user.getNickname();
    }

    public String getUpdatePassword(final User user, final UserUpdateRequest request) {
        if (request.isPasswordUpdate()) {
            return passwordEncoder.encode(request.getPassword());
        }
        return user.getPassword();
    }

}
