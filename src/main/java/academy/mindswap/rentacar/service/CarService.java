package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.CarUpdateDto;
import academy.mindswap.rentacar.model.Car;

import java.util.List;

public interface CarService {


    CarDto createCar(CarDto carDto);

    CarDto getCarById(Long carId);

    List<CarDto> getAllCars();

    CarDto updateCar(CarUpdateDto carUpdateDto);

    void deleteCar(Long carId);

    List<CarDto> createCars(List<CarDto> carDtos);

}
