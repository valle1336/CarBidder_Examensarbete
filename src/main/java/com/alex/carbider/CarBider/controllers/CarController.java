package com.alex.carbider.CarBider.controllers;

import com.alex.carbider.CarBider.entities.user.UserEntity;
import com.alex.carbider.CarBider.entities.user.UserRepository;
import com.alex.carbider.CarBider.entities.user.cars.CarEntity;
import com.alex.carbider.CarBider.entities.user.cars.CarEntityDetailsService;
import com.alex.carbider.CarBider.entities.user.cars.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    @Autowired
    private CarEntityDetailsService carService;

    @Autowired
    public CarController(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
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

    @GetMapping("/myCars")
    public String showMyCars(CarEntity carEntity, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity currentUser = userRepository.findUserByUsername(username);
        List<CarEntity> userCars = currentUser.getCarsList();


        model.addAttribute("userCars", userCars);

        return "myCars";
    }

    @GetMapping("/editMyCar")
    public String showEditCarPage(
            @RequestParam("carId")
            Long carId,
            Model model
    ) {
        CarEntity car = carRepository.findById(carId).orElse(null);

        if (car == null) {
            return "error.page";
        }
        model.addAttribute("carId", carId);
        model.addAttribute("carTitle", car.getTitle());
        model.addAttribute("carDescription", car.getDescription());
        model.addAttribute("carStartingBid", car.getStartingBid());
        model.addAttribute("carEmail", car.getEmail());
        model.addAttribute("carBuyOutPrice", car.getBuyOutPrice());

        return "editMyCar";
    }

    @PostMapping("/save-edit-car")
    public String saveCar(
            @RequestParam("carId") Long carId,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("email") String email,
            @RequestParam("startingBid") int startingBid,
            @RequestParam("buyOutPrice") int buyOutPrice)


    {

        CarEntity car = carRepository.findById(carId).orElse(null);
        if (car == null) {
            return "error-page";
        }

        car.setTitle(title);
        car.setDescription(description);
        car.setEmail(email);
        car.setStartingBid(startingBid);
        car.setBuyOutPrice(buyOutPrice);
        carRepository.save(car);

        return "redirect:/myCars";
    }

    @PostMapping("/place-bet")
    public String placeBetOnCar(
            @RequestParam("carId") Long carId,
            @RequestParam("currentBid") int currentBid)


    {

        CarEntity car = carRepository.findById(carId).orElse(null);
        if (car == null) {
            return "error-page";
        }

        car.setCurrentBid(currentBid);
        carRepository.save(car);

        return "redirect:/";
    }

    @GetMapping("/deleteCar/{id}")
    public String deleteCar(
            @PathVariable Long id
    ) {
        carRepository.deleteById(id);
        return "redirect:/myCars";
    }

}
