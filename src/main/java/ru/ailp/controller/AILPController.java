package ru.ailp.controller;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ailp.dto.LessonTestDto;
import ru.ailp.service.LessonTestService;
import ru.ailp.service.QuestionService;

@Controller
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AILPController {

    @NonNull LessonTestService lessonTestService;
    @NonNull QuestionService questionService;

    @Autowired
    public AILPController(LessonTestService lessonTestService, QuestionService questionService) {
        this.lessonTestService = lessonTestService;
        this.questionService = questionService;
    }

    @GetMapping("ailp/1/1")
    public String main() {
        return "main";
    }

    @GetMapping("ailp/1/2")
    public String test(Model model) {

        LessonTestDto lessonTestDto = lessonTestService.findByLessonIdAndPageId(1L, 2L);

        model.addAttribute("lessonTest", lessonTestDto);
        model.addAttribute("questions", questionService.findQuestionAnswerHelperListByTestId(lessonTestDto.getId()));

        return "test";
    }

    @GetMapping
    public String index() {
        return "index";
    }
}
