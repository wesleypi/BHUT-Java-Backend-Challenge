package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.model.LogModel;
import com.wesleypi.cars.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/api")
public class LogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/logs")
    @ResponseStatus(HttpStatus.OK)
    public List<LogModel> getLogs(){
        return logsService.getLog();
    }
}
