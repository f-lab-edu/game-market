package com.gamemarket.like.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductLikeTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() throws Exception {
        signUp();
        createProduct();
    }

    @Test
    @DisplayName("상품 좋아요 추가/조회/삭제 시나리오 테스트")
    void productLikeTest() throws Exception {
        createProductLikeTest();
        findAllProductLikeTest();
        deleteProductLikeTest();
    }

    private void createProductLikeTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        Product product = findProduct(session);

        mockMvc.perform(post("/like/product/{id}", product.getId())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private void findAllProductLikeTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        Product product = findProduct(session);

        mockMvc.perform(get("/like/product/{id}", product.getId())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private void deleteProductLikeTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        Product product = findProduct(session);

        mockMvc.perform(delete("/like/product/{id}", product.getId())
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private void signUp() throws Exception {
        User user = UserFixture.createUser("qwer@naver.com", "qwer", "qwer1234QW!", true);
        String request = UserFixture.ObjectToJson(user);

        mockMvc.perform(post("/user/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    private void createProduct() throws Exception {
        MockHttpSession session = getMockHttpSession();
        String request = ProductFixture.productRequest("aa", ProductCategory.ACTION, 100);

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

    private Product findProduct(MockHttpSession session) throws Exception {
        String request = String.format("{\"name\":\"%s\"}", "aa");

        MvcResult response = mockMvc.perform(get("/product/")
                        .param("name", "aa")
                        .param("sort", "id,asc")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        Product[] products = objectMapper.readValue(response.getResponse().getContentAsString(), Product[].class);
        return products[0];
    }

}
