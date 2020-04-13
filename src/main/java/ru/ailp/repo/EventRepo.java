package ru.ailp.repo;

import ru.ailp.entity.EventEntity;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.Optional;

public interface EventRepo extends CommonRepo<EventEntity> {

    Optional<EventEntity> findEventEntityByEventTypeAndEventName(String eventType, String eventName);
}