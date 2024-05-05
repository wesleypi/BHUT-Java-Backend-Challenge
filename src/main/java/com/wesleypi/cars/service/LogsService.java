package com.wesleypi.cars.service;

import com.wesleypi.cars.domain.helper.HelperConverter;
import com.wesleypi.cars.domain.model.LogModel;
import com.wesleypi.cars.domain.dto.LogResponse;
import com.wesleypi.cars.domain.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LogsService {

    @Autowired
    LogRepository logRepository;

    public void createLog(LogModel logModel) {
        logRepository.save(logModel);
    }

    public Page<LogResponse> getLog(int page, int size, String sortBy) {
        return logRepository.findAll(
                PageRequest.of(page, size, Sort.by(sortBy).descending()))
                .map(HelperConverter::toLogResponse);
    }
}
