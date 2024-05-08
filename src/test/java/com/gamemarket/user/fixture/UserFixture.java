package com.gamemarket.user.fixture;

import com.gamemarket.common.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class UserFixture {

    public static String createUserRequest(String email, String nickname, String password) {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("email", email);
        userMap.put("nickname", nickname);
        userMap.put("password", password);

        return JsonUtils.objectToJson(userMap);
    }

    public static String emailPasswordConvertRequest(String email, String password) {
        return String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
    }

    public static String nicknamePasswordConvertRequest(String nickname, String password) {
        return String.format("{\"nickname\":\"%s\",\"password\":\"%s\"}", nickname, password);
    }

}
