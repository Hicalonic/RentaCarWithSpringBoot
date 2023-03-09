package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;
    @Autowired
    public CarController (CarService carService) {
        this.carService = carService;
    }

    @GetMapping("")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarDto carDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
               carService.createCar(carDto);
        return new ResponseEntity<>(carDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "{carID}")
    public ResponseEntity<Car> getCarById(@PathVariable("carID") Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }

    @PutMapping(path = "{carId}")
    public  ResponseEntity<String> updateCar(@PathVariable("carId") Long id, @RequestBody CarDto carDto) {
        carDto.setId(id);
        carService.updateCar(carDto);
        return new ResponseEntity<>("Update Success", HttpStatus.OK) ;
    }

    @DeleteMapping(path = "{carID}")
    public ResponseEntity<String> deleteCar(@PathVariable("carID") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Car has been removed",HttpStatus.OK);
    }





}
