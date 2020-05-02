package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.AnswerDto;
import ru.ailp.entity.AnswerEntity;
import ru.ailp.mapper.AnswerMapper;
import ru.ailp.repo.AnswerRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("answerService")
public class AnswerService extends AbstractService<AnswerEntity, AnswerDto, AnswerRepo, AnswerMapper> {

    @NonNull
    static AnswerMapper answerMapper = Mappers.getMapper(AnswerMapper.class);
    @NonNull AnswerRepo answerRepo;

    @Autowired
    public AnswerService(AnswerRepo answerRepo) {
        super(answerRepo, answerMapper);
        this.answerRepo = answerRepo;
    }

    List<AnswerDto> findByQuestionId(Long questionId) {
        log.info("id объекта на входе: {}", questionId);

        return answerRepo.findByQuestionId(questionId)
                .map(answerMapper::entityListToDtoList)
                .orElseThrow(() -> new NotFoundException("Таких ответов не существует"));
    }
}