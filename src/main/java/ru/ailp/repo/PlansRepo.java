package ru.ailp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ailp.entity.PlansEntity;

public interface PlansRepo extends JpaRepository<PlansEntity, Long> {
}