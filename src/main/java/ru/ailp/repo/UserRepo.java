package ru.ailp.repo;

import ru.ailp.entity.UserEntity;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.Optional;

public interface UserRepo extends CommonRepo<UserEntity> {

    Optional<UserEntity> findByUsername(String username);
}