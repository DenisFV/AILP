package ru.ailp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Api(tags = "AILPController", description = "main")
public class AILPController {

    @GetMapping("ailp/1/1")
    @ApiOperation(value = "main", notes = "main")
    public String main() {
        return "main";
    }
}
