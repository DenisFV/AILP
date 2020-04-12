package ru.ailp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ailp.entity.EventsEntity;

import java.util.Optional;

public interface EventsRepo extends JpaRepository<EventsEntity, Long> {

    Optional<EventsEntity> getEventsEntityByEventTypeAndEventName(String eventType, String eventName);
}