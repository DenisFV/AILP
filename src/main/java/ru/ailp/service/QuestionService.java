package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.QuestionDto;
import ru.ailp.dto.helper.QuestionAnswerHelper;
import ru.ailp.entity.QuestionEntity;
import ru.ailp.mapper.QuestionMapper;
import ru.ailp.repo.QuestionRepo;
import ru.ailp.service.abstr.AbstractService;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("questionService")
public class QuestionService extends AbstractService<QuestionEntity, QuestionDto, QuestionRepo, QuestionMapper> {

    @NonNull
    static QuestionMapper questionMapper = Mappers.getMapper(QuestionMapper.class);
    @NonNull QuestionRepo questionRepo;
    @NonNull AnswerService answerService;

    @Autowired
    public QuestionService(QuestionRepo questionRepo, AnswerService answerService) {
        super(questionRepo, questionMapper);
        this.questionRepo = questionRepo;
        this.answerService = answerService;
    }

    private List<QuestionAnswerHelper> buildQuestionAnswerHelperList(List<QuestionAnswerHelper> questionAnswerHelperList) {
        return questionAnswerHelperList.stream()
                .map(e -> QuestionAnswerHelper.builder()
                        .id(e.getId())
                        .answerList(answerService.findByQuestionId(e.getId()))
                        .answerOption(e.getAnswerOption())
                        .comment(e.getComment())
                        .question(e.getQuestion())
                        .testId(e.getTestId())
                        .build()
                ).collect(Collectors.toList());
    }

    public List<QuestionDto> findByTestId(Long testId) {
        log.info("id объекта на входе: {}", testId);

        return questionRepo.findByTestId(testId)
                .map(questionMapper::entityListToDtoList)
                .orElseThrow(() -> new NotFoundException("Таких вопросов не существует"));
    }

    public List<QuestionAnswerHelper> findQuestionAnswerHelperListByTestId(Long lessonTestId) {
        log.info("id объекта на входе: {}", lessonTestId);

        return buildQuestionAnswerHelperList(questionMapper.questionDtoListToQuestionAnswerHelperList(findByTestId(lessonTestId)));
    }
}