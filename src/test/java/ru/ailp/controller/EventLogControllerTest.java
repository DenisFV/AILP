package ru.ailp.controller;

import ru.ailp.abstr.AbstractTest;
import ru.ailp.dto.EventLogDto;

class EventLogControllerTest extends AbstractTest {

    private final static String url = "/event-log";
    private final static EventLogDto entity = EventLogDto.builder().build();

    EventLogControllerTest() {
        super(url, entity);
    }
}