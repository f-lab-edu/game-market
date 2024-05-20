package com.gamemarket.user.fixture;

import com.gamemarket.common.utils.JsonUtils;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.ui.request.UserSignInRequest;
import com.gamemarket.user.ui.request.UserUpdateRequest;

public class UserFixture {

    public static User createUser(String email, String nickname, String password, boolean status) {
        User user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setStatus(status);

        return user;
    }

    public static UserSignInRequest userSignInRequest(String email, String password, boolean status) {
        UserSignInRequest userSignInRequest = new UserSignInRequest();
        userSignInRequest.setEmail(email);
        userSignInRequest.setPassword(password);
        userSignInRequest.setStatus(status);

        return userSignInRequest;
    }

    public static UserUpdateRequest userUpdateRequest(String nickname, String password) {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setNickname(nickname);
        userUpdateRequest.setPassword(password);

        return userUpdateRequest;
    }

    public static String ObjectToJson(User user) {
        return JsonUtils.objectToJson(user);
    }

    public static String emailPasswordConvertRequest(String email, String password, boolean status) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\",\"status\":%b}", email, password, status);
    }

    public static String nicknamePasswordConvertRequest(String nickname, String password) {
        return String.format("{\"nickname\":\"%s\",\"password\":\"%s\"}", nickname, password);
    }

}
