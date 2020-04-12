package ru.ailp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ailp.entity.EventLogEntity;

public interface EventLogRepo extends JpaRepository<EventLogEntity, Long> {
}