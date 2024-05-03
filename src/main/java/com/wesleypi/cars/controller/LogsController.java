package com.wesleypi.cars.controller;

import com.wesleypi.cars.domain.model.LogModel;
import com.wesleypi.cars.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
public class LogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/logs")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<LogModel>> getLogs(){
        return ResponseEntity.of(
                Optional.ofNullable(logsService.getLog()));
    }
}
