package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.LessonTestDto;
import ru.ailp.entity.LessonTestEntity;
import ru.ailp.mapper.LessonTestMapper;
import ru.ailp.repo.LessonTestRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("lessonTestService")
public class LessonTestService extends AbstractService<LessonTestEntity, LessonTestDto, LessonTestRepo, LessonTestMapper> {

    @NonNull
    static LessonTestMapper lessonTestMapper = Mappers.getMapper(LessonTestMapper.class);
    @NonNull LessonTestRepo lessonTestRepo;

    @Autowired
    public LessonTestService(LessonTestRepo lessonTestRepo) {
        super(lessonTestRepo, lessonTestMapper);
        this.lessonTestRepo = lessonTestRepo;
    }

    public LessonTestDto findByLessonIdAndPageId(Long lessonId, Long pageId){
        log.info("id объектов на входе: {}, {}", lessonId, pageId);

        return lessonTestRepo.findByLessonIdAndPageId(lessonId, pageId)
                .map(lessonTestMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException("Такого теста не существует"));
    }
}