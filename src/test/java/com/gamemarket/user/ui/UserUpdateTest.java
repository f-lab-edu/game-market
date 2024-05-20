package com.gamemarket.user.ui;

import com.gamemarket.common.utils.JsonUtils;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.fixture.UserFixture;
import com.gamemarket.user.ui.request.UserSignInRequest;
import com.gamemarket.user.ui.request.UserUpdateRequest;
import org.junit.jupiter.api.BeforeEach;
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
import static com.gamemarket.user.fixture.UserFixture.nicknamePasswordConvertRequest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserUpdateTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void signUp() throws Exception {
        User user = UserFixture.createUser("qwer@naver.com", "qwer", "qwer1234QW!", true);
        String request = UserFixture.ObjectToJson(user);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("회원정보변경 성공 테스트")
    void profileUpdateSuccessTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        UserUpdateRequest userUpdate = UserFixture.userUpdateRequest("qwerqwer", "qwer1234QW!");
        String request = JsonUtils.objectToJson(userUpdate);

        mockMvc.perform(patch("/user/update")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
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
