package ru.ailp.service;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ailp.dto.TestResultDto;
import ru.ailp.entity.TestResultEntity;
import ru.ailp.mapper.TestResultMapper;
import ru.ailp.repo.TestResultRepo;
import ru.ailp.service.abstr.AbstractService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Service("testResultService")
public class TestResultService extends AbstractService<TestResultEntity, TestResultDto, TestResultRepo, TestResultMapper> {

    @NonNull
    static TestResultMapper testResultMapper = Mappers.getMapper(TestResultMapper.class);
    @NonNull TestResultRepo testResultRepo;

    @Autowired
    public TestResultService(TestResultRepo testResultRepo) {
        super(testResultRepo, testResultMapper);
        this.testResultRepo = testResultRepo;
    }

    public List<TestResultDto> buildTestResultDtoList(List<TestResultDto> testResultDtoList) {
        return testResultDtoList.stream()
                .map(e -> TestResultDto.builder()
                        .id(e.getId())
                        .answerId(e.getAnswerId())
                        .questionId(e.getQuestionId())
                        .result(e.getResult())
                        .testId(e.getTestId())
                        .comment(e.getComment())
//                        .userId(UserService.getAuthenticationUser().getId())
                        .build()
                ).collect(Collectors.toList());
    }
}