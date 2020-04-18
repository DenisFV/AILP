package ru.ailp.controller;

import ru.ailp.abstr.AbstractTest;
import ru.ailp.dto.EventDto;

class EventControllerTest extends AbstractTest {

    private final static String url = "/event";
    private final static EventDto entity = EventDto.builder().build();

    EventControllerTest() {
        super(url, entity, EventController.class);
    }
}