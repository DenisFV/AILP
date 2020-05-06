package ru.ailp.controller;

import io.swagger.annotations.Api;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ailp.controller.abstr.AbstractController;
import ru.ailp.dto.TestResultDto;
import ru.ailp.service.TestResultService;

import java.util.List;

@RestController
@RequestMapping("test-result")
@Api(tags = "TestResultController", description = "TestResultController")
public class TestResultController extends AbstractController<TestResultDto, TestResultService> {

    @NonNull TestResultService testResultService;

    @Autowired
    public TestResultController(TestResultService testResultService) {
        super(testResultService);
        this.testResultService = testResultService;
    }

    @PostMapping("/1/2")
    public ResponseEntity<List<TestResultDto>> testSave(@RequestBody List<TestResultDto> testResultDtoList) {

        return testResultService.saveAll(testResultService.buildTestResultDtoList(testResultDtoList))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
