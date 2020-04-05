package ru.ailp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ailp.entity.UserEventEntity;

@Repository
public interface UserEventRepo extends JpaRepository<UserEventEntity, Long> {
}