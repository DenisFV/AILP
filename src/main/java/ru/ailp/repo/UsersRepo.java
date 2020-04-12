package ru.ailp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ailp.entity.UsersEntity;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<UsersEntity, Long> {

    Optional<UsersEntity> findByUsername(String username);

}