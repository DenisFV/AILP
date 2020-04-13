package ru.ailp.repo.abstr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.ailp.entity.abstr.AbstractEntity;

@NoRepositoryBean
public interface CommonRepo<T extends AbstractEntity> extends JpaRepository<T, Long> {
}