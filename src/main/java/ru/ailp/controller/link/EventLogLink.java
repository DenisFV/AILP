package ru.ailp.controller.link;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import ru.ailp.controller.EventLogController;
import ru.ailp.dto.EventLogDto;
import ru.ailp.dto.helper.EventLogHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EventLogLink extends DefaultLink<EventLogDto> {

    public EventLogLink(EventLogController controller) {
        super(controller);
    }

    @Override
    public EntityModel<EventLogDto> toModel(EventLogDto eventLogDto) {
        return new EntityModel<>(eventLogDto, addLinks(eventLogDto, Collections.emptyList()));
    }

    @Override
    Link[] addLinks(EventLogDto eventLogDto, List<Link> links) {
        List<Link> linkList = new ArrayList<>();

        linkList.add(linkTo(methodOn(EventLogController.class).saveEventLogHelper(eventLogDto.getPageId(), eventLogDto.getLessonId(), new EventLogHelper()))
                .withRel("saveEventLogHelper")
                .withType(HttpMethod.POST.name())
                .withTitle("Сохранить EventLogHelper обект"));

        return super.addLinks(eventLogDto, linkList);
    }
}