package ru.ailp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.ailp.abstr.DefaultTest;
import ru.ailp.dto.EventDto;
import ru.ailp.dto.EventLogDto;
import ru.ailp.dto.helper.EventLogEventHelper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EventLogControllerTest extends DefaultTest {

    private final static String url = "/event-log";
    private final static EventLogDto entity = EventLogDto.builder().build();

    EventLogControllerTest() {
        super(url, entity);
    }

    @SneakyThrows
    @Test
    void saveEventLogEventHelper() {
        String eventLogEventHelper = new ObjectMapper().writeValueAsString(
                EventLogEventHelper.builder()
                        .eventDto(EventDto.builder().eventName("back").eventType("click").build())
                        .build());

        setAuthAdminContext();

        this.mockMvcWithAuthorization.perform(post(url + "/1/1")
                .with(user("admin").password("1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(eventLogEventHelper))
                .andDo(print())
                .andExpect(status().isOk());
    }
}