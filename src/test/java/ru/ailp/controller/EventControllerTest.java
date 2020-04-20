package ru.ailp.controller;

import ru.ailp.abstr.DefaultTest;
import ru.ailp.dto.EventDto;

class EventControllerTest extends DefaultTest {

    private final static String url = "/event";
    private final static EventDto entity = EventDto.builder().build();

    EventControllerTest() {
        super(url, entity);
    }
}