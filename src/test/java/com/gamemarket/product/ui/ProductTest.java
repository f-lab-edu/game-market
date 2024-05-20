package com.gamemarket.product.ui;

import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.fixture.ProductFixture;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.fixture.UserFixture;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductTest {

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
    @DisplayName("상품등록 테스트")
    void createProductTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        String request = ProductFixture.productCreateRequest("aa", ProductCategory.ACTION, 100);

        mockMvc.perform(post("/product/")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private MockHttpSession getMockHttpSession() throws Exception {
        String request = emailPasswordConvertRequest("qwer@naver.com", "qwer1234QW!", true);

        MvcResult result = mockMvc.perform(post("/user/sign-in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andReturn();

        return (MockHttpSession) result.getRequest().getSession(false);
    }

}
