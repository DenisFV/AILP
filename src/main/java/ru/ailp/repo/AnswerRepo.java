package ru.ailp.repo;

import ru.ailp.entity.AnswerEntity;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.List;
import java.util.Optional;

public interface AnswerRepo extends CommonRepo<AnswerEntity> {

    Optional<List<AnswerEntity>> findByQuestionId(Long questionId);
}