package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.helper.HelperConverter;
import com.wesleypi.cars.domain.dto.LogResponse;
import com.wesleypi.cars.domain.dto.LogWithPagination;
import com.wesleypi.cars.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/logs")
    @ResponseStatus(HttpStatus.OK)
    public LogWithPagination getLogs(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "creationDateHour") String sortBy) {
        Page<LogResponse> logModelPage = logsService.getLog(page, size, sortBy);

        return LogWithPagination.builder()
                .logs(logModelPage.getContent())
                .paginationModel(HelperConverter.pageToResponse(logModelPage))
                .build();
    }
}
