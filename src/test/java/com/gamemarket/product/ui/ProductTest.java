package com.gamemarket.product.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamemarket.common.utils.JsonUtils;
import com.gamemarket.product.domain.ProductCategory;
import com.gamemarket.product.domain.entity.Product;
import com.gamemarket.product.fixture.ProductFixture;
import com.gamemarket.product.ui.request.ProductCreateRequest;
import com.gamemarket.product.ui.request.ProductFindRequest;
import com.gamemarket.product.ui.request.ProductUpdateRequest;
import com.gamemarket.user.domain.entity.User;
import com.gamemarket.user.fixture.UserFixture;
import com.gamemarket.user.ui.request.UserSignInRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
        ProductCreateRequest productCreate = ProductFixture.productCreateRequest("aa", ProductCategory.ACTION, 100);
        String request = JsonUtils.objectToJson(productCreate);

        mockMvc.perform(post("/product/")
                        .session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("상품조회/변경 테스트")
    void findUpdateTest() throws Exception {
        MockHttpSession session = getMockHttpSession();
        createProductTest();
        Product product = findProduct(session);
        updateProduct(session, product);
    }

    private Product findProduct(MockHttpSession session) throws Exception {
        ProductFindRequest productFind = ProductFixture.productFindRequest("aa");
        String request = JsonUtils.objectToJson(productFind);

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

    private void updateProduct(MockHttpSession session, Product product) throws Exception {
        ProductUpdateRequest productUpdate = ProductFixture.productUpdateRequest("aa");
        String request = JsonUtils.objectToJson(productUpdate);

        mockMvc.perform(patch("/product/{id}", product.getId())
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
