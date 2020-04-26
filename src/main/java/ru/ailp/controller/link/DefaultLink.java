package ru.ailp.controller.link;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import ru.ailp.controller.abstr.AbstractController;
import ru.ailp.entity.abstr.AbstractEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultLink<T extends AbstractEntity> implements RepresentationModelAssembler<T, EntityModel<T>> {

    AbstractController controller;

    public DefaultLink(AbstractController controller) {
        this.controller = controller;
    }

    @Override
    public EntityModel<T> toModel(T t) {
        return new EntityModel<>(t, addLinks(t, Collections.emptyList()));
    }

    @SuppressWarnings("unchecked")
    Link[] addLinks(T t, List<Link> links) {
        List<Link> linkList = new ArrayList<>(links);

        linkList.add(linkTo(methodOn(controller.getClass()).head(t.getId()))
                .withRel("head")
                .withType(HttpMethod.HEAD.name())
                .withTitle("Существование обекта"));

        linkList.add(linkTo(methodOn(controller.getClass()).findById(t.getId()))
                .withRel("findById")
                .withType(HttpMethod.GET.name())
                .withTitle("Поиск обекта по ИД"));

        linkList.add(linkTo(methodOn(controller.getClass()).findAll())
                .withRel("findAll")
                .withType(HttpMethod.GET.name())
                .withTitle("Поиск всех объектов"));

        linkList.add(linkTo(methodOn(controller.getClass()).save(t))
                .withRel("save")
                .withType(HttpMethod.POST.name())
                .withTitle("Сохранить/Обновить обекта"));

        linkList.add(linkTo(methodOn(controller.getClass()).deleteById(t.getId()))
                .withRel("deleteById")
                .withType(HttpMethod.DELETE.name())
                .withTitle("Удалить обект"));

        linkList.add(linkTo(methodOn(controller.getClass()).deleteAll())
                .withRel("deleteAll")
                .withType(HttpMethod.DELETE.name())
                .withTitle("Удалить все обекты"));

        String self = getMethodName();
        return linkList.stream()
                .map(e -> e.getRel().value().equals(self) ? e.withSelfRel() : e)
                .toArray(Link[]::new);
    }

    private String getMethodName() {
        return Objects.requireNonNull(Stream.of(new Throwable().getStackTrace())
                .filter(e -> e.getClassName().contains(".controller."))
                .reduce((f, s) -> s).orElse(null))
                .getMethodName();
    }
}