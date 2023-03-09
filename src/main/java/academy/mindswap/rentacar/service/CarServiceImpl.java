package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.CarConverter;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {


    private CarRepository carRepository;

    private CarConverter carConverter;
    @Autowired

    public CarServiceImpl (CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }



    @Override
    public Car createCar(CarDto carDto) {
       Car car =  carConverter.fromCarDtoToCarEntity(carDto);
       carRepository.save(car);
       return car;
    }

    @Override
    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElseThrow(() -> new IllegalStateException("car Id doesn't exist"));
    }



    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    @Transactional
    @Override
    public CarDto updateCar(CarDto carDto) {




return null;

//     Car carToUpdate = carConverter.fromCarDtoToCarEntity(carDto);
//     carToUpdate.setId(carDto.getId());
//     carToUpdate.setBrand(carDto.getBrand());
//     carToUpdate.setModel(carDto.getModel());
//     carToUpdate.setLicensePlate(carDto.getLicensePlate());
//     carToUpdate.setManufacturingYear(carDto.getManufacturingYear());
//     carToUpdate.setCostPerHour(carDto.getCostPerHour());
//     return carConverter.fromCarEntityToCarDto(carToUpdate);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }
}
