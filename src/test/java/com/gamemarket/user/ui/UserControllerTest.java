package com.gamemarket.user.ui;

import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.common.utils.JsonUtils;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원가입 시나리오 테스트")
    void signUpTest() throws Exception {
        signUpSuccessTest();
        existsEmailTest();
        existsNicknameTest();
    }

    void signUpSuccessTest() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("email", "abcd@naver.com");
        request.put("nickname", "abcd");
        request.put("password", "qwer1234QW!");

        String json = new Gson().toJson(request);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    void existsEmailTest() throws Exception {
        Map<String, String> existsEmailRequest = new HashMap<>();
        existsEmailRequest.put("email", "abcd@naver.com");
        existsEmailRequest.put("nickname", "abcdabcd");
        existsEmailRequest.put("password", "qwer1234QW!");

        String existsEmailJson = JsonUtils.objectToJson(existsEmailRequest);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(existsEmailJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(UserExceptionCode.EXISTS_USER_EMAIL.getCode()))
                .andExpect(jsonPath("$.message").value(UserExceptionCode.EXISTS_USER_EMAIL.getMessage()))
                .andDo(print());
    }

    void existsNicknameTest() throws Exception {
        Map<String, String> existsNicknameRequest = new HashMap<>();
        existsNicknameRequest.put("email", "abcdabcd@naver.com");
        existsNicknameRequest.put("nickname", "abcd");
        existsNicknameRequest.put("password", "qwer1234QW!");

        String existsNicknameJson = JsonUtils.objectToJson(existsNicknameRequest);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(existsNicknameJson))
                .andExpect(jsonPath("$.code").value(UserExceptionCode.EXISTS_USER_NICKNAME.getCode()))
                .andExpect(jsonPath("$.message").value(UserExceptionCode.EXISTS_USER_NICKNAME.getMessage()))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}