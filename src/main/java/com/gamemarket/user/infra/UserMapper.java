package com.gamemarket.user.infra;

import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.ui.request.UserSignUpRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "request.email", target = "email")
    @Mapping(source = "request.nickname", target = "nickname")
    @Mapping(source = "encryptPassword", target = "password")
    @Mapping(target = "status", constant = "true")
    User user(final UserSignUpRequest request, final String encryptPassword);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "requestNickname", target = "nickname")
    @Mapping(source = "requestPassword", target = "password")
    User updateUser(final Long userId, final String requestNickname, final String requestPassword);

    @Mapping(source = "user.id", target = "id")
    @Mapping(target = "status", constant = "false")
    User deleteUser(final User user);

}
