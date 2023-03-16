package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.CarUpdateDto;
import academy.mindswap.rentacar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {



    private CarService carService;
    @Autowired
    public CarController (CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/client/employee")
    public ResponseEntity<List<CarDto>> getCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping(path = "/client/employee/{carID}")
    public ResponseEntity<CarDto> getCarById(@PathVariable("carID") Long id) {
        return new ResponseEntity<>(carService.getCarById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/client/employee")
    public ResponseEntity<CarDto> createCar(@Valid @RequestBody CarDto carDto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
             CarDto carDto1 = carService.createCar(carDto);
        return new ResponseEntity<>(carDto1, HttpStatus.CREATED);
    }

    @PostMapping(path = "/client/employee/admin")
    public ResponseEntity<List<CarDto>> createCars(@Valid @RequestBody List<CarDto> carDtos, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        carDtos.stream().forEach(c -> carService.createCar(c));

        return  new ResponseEntity<>(carDtos,HttpStatus.OK);
    }



    @PutMapping(path = "/client/employee/{carId}")
    public  ResponseEntity<CarDto> updateCar(@PathVariable("carId") Long id, @RequestBody CarUpdateDto carUpdateDto) {
        carUpdateDto.setId(id);
      CarDto carDto =  carService.updateCar(carUpdateDto);
        return new ResponseEntity<>(carDto, HttpStatus.OK) ;
    }

    @DeleteMapping(path = "/client/employee/{carID}")
    public ResponseEntity<String> deleteCar(@PathVariable("carID") Long id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Car has been removed",HttpStatus.OK);
    }





}
