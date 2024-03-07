package com.alex.carbider.CarBider.entities.cars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

    CarEntity findProductByTitle(String title);

}
