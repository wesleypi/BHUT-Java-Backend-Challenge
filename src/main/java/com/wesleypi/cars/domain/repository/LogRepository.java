package com.wesleypi.cars.domain.repository;

import com.wesleypi.cars.domain.model.LogModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends MongoRepository<LogModel, String> {
}
