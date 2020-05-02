package ru.ailp.repo;

import ru.ailp.entity.LessonTestEntity;
import ru.ailp.repo.abstr.CommonRepo;

import java.util.Optional;

public interface LessonTestRepo extends CommonRepo<LessonTestEntity> {

    Optional<LessonTestEntity> findByLessonIdAndPageId(Long lessonId, Long pageId);

}