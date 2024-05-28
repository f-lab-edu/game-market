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
        return new UserSignInRequest(email, password, status);
    }

    public static UserUpdateRequest userUpdateRequest(String nickname, String password) {
        return new UserUpdateRequest(nickname, password);
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
