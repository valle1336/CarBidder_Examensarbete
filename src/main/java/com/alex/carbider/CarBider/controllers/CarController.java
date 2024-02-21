package com.alex.carbider.CarBider.controllers;

import com.alex.carbider.CarBider.entities.user.cars.CarEntity;
import com.alex.carbider.CarBider.entities.user.cars.CarEntityDetailsService;
import com.alex.carbider.CarBider.entities.user.cars.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CarController {

    private final CarRepository carRepository;

    @Autowired
    private CarEntityDetailsService carService;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/createAd")
    public String createAdPage(CarEntity carEntity, Model model) {
        model.addAttribute("carEntity", carEntity);

        return "createAd";

    }

    @PostMapping("/createAd")
    public String createAd(
            @Valid CarEntity carEntity,
            BindingResult result
    ) {
        if(result.hasErrors()) {
            return "createAd";
        }

        carRepository.save(carEntity);

        return "redirect:/";
    }

    @GetMapping("/")
    public String showAllCarsOnHomePage(CarEntity carEntity, Model model) {
        List<CarEntity> cars = carRepository.findAll();
        model.addAttribute("cars", cars);

        return "home";
    }

    @GetMapping("/viewCar/{id}")
    public String getDynamicCarView(@PathVariable("id") Long id, Model model) {
        CarEntity car = carService.findById(id);

        if (car == null) {
            return "redirect:/error";
        }
        model.addAttribute("carEntity", car);
        return "viewCar";
    }

}
