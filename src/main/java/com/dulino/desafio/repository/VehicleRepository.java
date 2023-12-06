package com.dulino.desafio.repository;

import com.dulino.desafio.entity.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehicleRepository extends MongoRepository<Vehicle,String> {
    boolean existsByPlate(String plate);
    boolean existsByPlateAndAndOwner_Id(String plate,String id);
    List<Vehicle> findByOwnerId(String id);
}
