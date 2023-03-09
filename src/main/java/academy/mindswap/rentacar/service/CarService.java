package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.model.Car;

import java.util.List;

public interface CarService {


    Car createCar(CarDto car);

    Car getCarById(Long carId);

    List<Car> getAllCars();

    CarDto updateCar(CarDto carDto);

    void deleteCar(Long carId);
}
