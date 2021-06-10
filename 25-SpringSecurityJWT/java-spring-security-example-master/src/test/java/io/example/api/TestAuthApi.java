package io.example.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.example.api.data.UserTestDataFactory;
import io.example.domain.dto.AuthRequest;
import io.example.domain.dto.CreateUserRequest;
import io.example.domain.dto.UserView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static io.example.util.JsonHelper.fromJson;
import static io.example.util.JsonHelper.toJson;
import static java.lang.System.currentTimeMillis;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestAuthApi {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final UserTestDataFactory userTestDataFactory;

    private final String password = "Test12345_";

    @Autowired
    public TestAuthApi(MockMvc mockMvc, ObjectMapper objectMapper, UserTestDataFactory userTestDataFactory) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.userTestDataFactory = userTestDataFactory;
    }

    @Test
    public void testLoginSuccess() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d@nix.io", currentTimeMillis()), "Test User", password);

        AuthRequest request = new AuthRequest();
        request.setUsername(userView.getUsername());
        request.setPassword(password);

        MvcResult createResult = this.mockMvc
                .perform(post("/api/public/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, request)))
                .andExpect(status().isOk())
                .andExpect(header().exists(HttpHeaders.AUTHORIZATION))
                .andReturn();

        UserView authUserView = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserView.class);
        assertEquals(userView.getId(), authUserView.getId(), "User ids must match!");
    }

    @Test
    public void testLoginFail() throws Exception {
        UserView userView = userTestDataFactory.createUser(String.format("test.user.%d@nix.io", currentTimeMillis()), "Test User", password);

        AuthRequest request = new AuthRequest();
        request.setUsername(userView.getUsername());
        request.setPassword("zxc");

        this.mockMvc
                .perform(post("/api/public/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, request)))
                .andExpect(status().isUnauthorized())
                .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION))
                .andReturn();
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        CreateUserRequest goodRequest = new CreateUserRequest();
        goodRequest.setUsername(String.format("test.user.%d@nix.com", currentTimeMillis()));
        goodRequest.setFullName("Test User A");
        goodRequest.setPassword(password);
        goodRequest.setRePassword(password);

        MvcResult createResult = this.mockMvc
                .perform(post("/api/public/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, goodRequest)))
                .andExpect(status().isOk())
                .andReturn();

        UserView userView = fromJson(objectMapper, createResult.getResponse().getContentAsString(), UserView.class);
        assertNotNull(userView.getId(), "User id must not be null!");
        assertEquals(goodRequest.getFullName(), userView.getFullName(), "User fullname  update isn't applied!");
    }

    @Test
    public void testRegisterFail() throws Exception {
        CreateUserRequest badRequest = new CreateUserRequest();
        badRequest.setUsername("invalid.username");

        this.mockMvc
                .perform(post("/api/public/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(objectMapper, badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Method argument validation failed")));
    }

}
