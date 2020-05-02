package ru.ailp.repo;

import ru.ailp.entity.QuestionEntity;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface QuestionRepo extends CommonRepo<QuestionEntity> {

    Optional<List<QuestionEntity>> findByTestId(Long testId);

}