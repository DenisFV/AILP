package ru.ailp.abstr;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.ailp.entity.abstr.AbstractEntity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class AbstractTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    public MockMvc mockMvcWithOutAuthorization;

    @Autowired
    public MockMvc mockMvcWithAuthorization;

    @BeforeEach
    void setUp() {
        this.mockMvcWithAuthorization = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    private final String url;
    private AbstractEntity entity;

    public AbstractTest(String url, AbstractEntity entity) {
        this.url = url;
        this.entity = entity;
    }

    @Value("${server.address}")
    private String host;

    @SneakyThrows
    @Test
    void withAuth() {
        this.mockMvcWithAuthorization.perform(get(url + "/test"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void withOutAuth() {
        this.mockMvcWithOutAuthorization.perform(get(url + "/test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://" + host + "/login"))
                .andExpect(status().is3xxRedirection());
    }

    @SneakyThrows
    @Test
    void headHttp() {
        this.mockMvcWithAuthorization.perform(head(url + "/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    void findById() {
        this.mockMvcWithAuthorization.perform(get(url + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void findAll() {
        this.mockMvcWithAuthorization.perform(get(url + "/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void save() {
        this.mockMvcWithAuthorization.perform(post(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void deleteById() {
        this.mockMvcWithAuthorization.perform(delete(url + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    void deleteAll() {
        this.mockMvcWithAuthorization.perform(delete(url + "/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
