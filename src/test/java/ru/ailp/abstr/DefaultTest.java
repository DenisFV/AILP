package ru.ailp.abstr;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import ru.ailp.entity.abstr.AbstractEntity;

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@TestMethodOrder(OrderAnnotation.class)
public abstract class DefaultTest extends AbstractTest {

    @Value("${server.address}")
    String host;
    @NonNull String url;
    @NonNull AbstractEntity entity;

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
    @Order(1)
    void headHttp() {
        this.mockMvcWithAuthorization.perform(head(url + "/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @SneakyThrows
    @Test
    @Order(2)
    void findById() {
        this.mockMvcWithAuthorization.perform(get(url + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @Order(3)
    void findAll() {
        this.mockMvcWithAuthorization.perform(get(url + "/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @Order(4)
    void save() {
        this.mockMvcWithAuthorization.perform(post(url + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(entity)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @Order(5)
    void deleteById() {
        this.mockMvcWithAuthorization.perform(delete(url + "/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    @Order(6)
    void deleteAll() {
        this.mockMvcWithAuthorization.perform(delete(url + "/"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
