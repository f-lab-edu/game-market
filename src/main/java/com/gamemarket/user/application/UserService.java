package com.gamemarket.user.application;

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
        final User user = User.builder()
                .email(request.getEmail())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean validateDuplicateEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean validateDuplicateNickname(final String nickname) {
        return userRepository.existsByNickname(nickname);
    }

}
