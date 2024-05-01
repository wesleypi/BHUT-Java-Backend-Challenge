package com.wesleypi.cars.service;

import com.wesleypi.cars.model.Log;
import com.wesleypi.cars.model.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService {

    @Autowired
    LogRepository logRepository;

    public List<Log> getLog(){
        return logRepository.findAll();
    }

    public Log saveLog(Log log){
        return logRepository.save(log);
    }
}
