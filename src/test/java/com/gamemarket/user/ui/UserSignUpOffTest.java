package com.gamemarket.user.ui;

import com.gamemarket.common.exception.user.UserExceptionCode;
import com.gamemarket.common.utils.JsonUtils;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.fixture.UserFixture;
import com.gamemarket.user.ui.request.UserSignInRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static com.gamemarket.user.fixture.UserFixture.emailPasswordConvertRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserSignUpOffTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원가입 시나리오 테스트")
    void signUpTest() throws Exception {
        signUpSuccessTest();
        existsEmailTest();
        existsNicknameTest();
        signOffTest();
    }

    void signUpSuccessTest() throws Exception {
        User user = UserFixture.createUser("qwer@naver.com", "qwer", "qwer1234QW!", true);
        String request = UserFixture.ObjectToJson(user);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    void existsEmailTest() throws Exception {
        User user = UserFixture.createUser("qwer@naver.com", "qwerqwer", "qwer1234QW!", true);
        String request = UserFixture.ObjectToJson(user);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(UserExceptionCode.EXISTS_USER_EMAIL.getCode()))
                .andExpect(jsonPath("$.message").value(UserExceptionCode.EXISTS_USER_EMAIL.getMessage()))
                .andDo(print());
    }

    void existsNicknameTest() throws Exception {
        User user = UserFixture.createUser("qwerqwer@naver.com", "qwer", "qwer1234QW!", true);
        String request = UserFixture.ObjectToJson(user);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(jsonPath("$.code").value(UserExceptionCode.EXISTS_USER_NICKNAME.getCode()))
                .andExpect(jsonPath("$.message").value(UserExceptionCode.EXISTS_USER_NICKNAME.getMessage()))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    void signOffTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        String request = String.format("{\"password\":\"%s\"}", "qwer1234QW!");

        mockMvc.perform(patch("/user/sign-off")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    private MockHttpSession getMockHttpSession() throws Exception {
        UserSignInRequest userSignIn = UserFixture.userSignInRequest("qwer@naver.com", "qwer1234QW!", true);
        String request = JsonUtils.objectToJson(userSignIn);

        MvcResult result = mockMvc.perform(post("/user/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andReturn();

        return (MockHttpSession) result.getRequest().getSession(false);
    }

}