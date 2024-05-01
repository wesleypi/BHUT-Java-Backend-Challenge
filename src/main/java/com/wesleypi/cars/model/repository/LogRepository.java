package com.wesleypi.cars.model.repository;

import com.wesleypi.cars.model.Log;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {

}
