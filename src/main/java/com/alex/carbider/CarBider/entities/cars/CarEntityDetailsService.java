package com.alex.carbider.CarBider.entities.cars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarEntityDetailsService {


    private final CarRepository carRepository;

    @Autowired
    public CarEntityDetailsService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public CarEntity findByTitle(String title) {
        return carRepository.findProductByTitle(title);
    }
    public CarEntity findById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
}
