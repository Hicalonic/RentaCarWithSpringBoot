package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.CarConverter;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.CarUpdateDto;
import academy.mindswap.rentacar.exceptions.LicensePlateException;
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
    public CarDto createCar(CarDto carDto) {

       boolean licensePlate = carRepository.findAll().stream().anyMatch(c -> c.getLicensePlate().equals(carDto.getLicensePlate()));
       if(licensePlate) {
           throw new LicensePlateException("license plate already exists");
       }
       Car car =  carConverter.fromCarDtoToCarEntity(carDto);
       carRepository.save(car);

       return carConverter.fromCarEntityToCarDto(car);
    }

    @Override
    public CarDto getCarById(Long carId) {
        Car car = carRepository.getReferenceById(carId);
        return carConverter.fromCarEntityToCarDto(car);
    }



    @Override
    public List<CarDto> getAllCars() {
        List<Car> carsList = carRepository.findAll();
        List<CarDto> carsDtos = carsList.stream()
                .map(car -> carConverter.fromCarEntityToCarDto(car))
                .toList();
        return carsDtos;
    }


    @Transactional
    @Override
    public CarDto updateCar(CarUpdateDto carUpdateDto) {
     Car carToUpdate = carRepository.getReferenceById(carUpdateDto.getId());
     carToUpdate.setId(carUpdateDto.getId());
     carToUpdate.setBrand(carUpdateDto.getBrand());
     carToUpdate.setModel(carUpdateDto.getModel());
     carToUpdate.setLicensePlate(carUpdateDto.getLicensePlate());
     carToUpdate.setManufacturingYear(carUpdateDto.getManufacturingYear());
     carToUpdate.setCostPerHour(carUpdateDto.getCostPerHour());
     return carConverter.fromCarEntityToCarDto(carToUpdate);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public List<CarDto> createCars(List<CarDto> carDtos) {
        List<Car> carsList =  carDtos.stream().map(car -> carConverter.fromCarDtoToCarEntity(car)).toList();
        List<CarDto> carDtoList = carsList.stream().map(car -> carConverter.fromCarEntityToCarDto(car)).toList();
        carsList.stream().map(car -> carRepository.save(car));

        return carDtoList;
    }
}
