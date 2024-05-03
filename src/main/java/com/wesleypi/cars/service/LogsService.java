package com.wesleypi.cars.service;

import com.wesleypi.cars.domain.model.LogModel;
import com.wesleypi.cars.domain.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService {

    @Autowired
    LogRepository logRepository;

    public List<LogModel> getLog(){
        return logRepository.findAll();
    }

    public LogModel create(LogModel logModel){
        return logRepository.save(logModel);
    }
}
