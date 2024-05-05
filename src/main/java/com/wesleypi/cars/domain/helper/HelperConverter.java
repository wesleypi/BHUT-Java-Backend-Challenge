package com.wesleypi.cars.domain.helper;

import com.wesleypi.cars.domain.dto.CreateCarRequest;
import com.wesleypi.cars.domain.model.LogModel;
import com.wesleypi.cars.domain.dto.LogResponse;
import com.wesleypi.cars.domain.dto.Pagination;
import com.wesleypi.cars.domain.dto.bhut.BhutCreateCar;
import org.springframework.data.domain.Page;

public interface HelperConverter {

    static Pagination pageToResponse(Page page){
        return Pagination.builder()
                .page(page.getNumber())
                .pageSize(page.getSize())
                .total(page.getTotalElements())
                .build();
    }

    static LogResponse toLogResponse(LogModel logModel){
        return LogResponse.builder()
                .carId(logModel.getCarId())
                .creationDateHour(logModel.getCreationDateHour())
                .processingDateHour(logModel.getProcessingDateHour())
                .build();
    }

    static BhutCreateCar toBhutCarRequest(CreateCarRequest carRequest) {
        return BhutCreateCar.builder()
                .brand(carRequest.getBrand())
                .name(carRequest.getName())
                .price(carRequest.getPrice())
                .build();
    }
}
