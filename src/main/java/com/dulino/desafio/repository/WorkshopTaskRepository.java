package com.dulino.desafio.repository;

import com.dulino.desafio.entity.WorkshopTask;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkshopTaskRepository extends MongoRepository<WorkshopTask,String> {
}
