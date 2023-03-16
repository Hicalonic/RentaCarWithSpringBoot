package academy.mindswap.rentacar.converter;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.CarUpdateDto;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.User;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public CarDto fromCarEntityToCarDto(Car car) {
        return CarDto.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .costPerHour(car.getCostPerHour())
                .licensePlate(car.getLicensePlate())
                .manufacturingYear(car.getManufacturingYear())
                .build();
    }

    public  Car fromCarDtoToCarEntity(CarDto carDto) {
       return Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .costPerHour(carDto.getCostPerHour())
                .licensePlate(carDto.getLicensePlate())
                .manufacturingYear(carDto.getManufacturingYear())
                .build();
    }

    public Car fromCarCreateDtoToEntity (CarDto carDto){
        return Car.builder()
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .costPerHour(carDto.getCostPerHour())
                .licensePlate(carDto.getLicensePlate())
                .manufacturingYear(carDto.getManufacturingYear())
                .build();
    }

    public Car fromCarUpdaterDtoToEntity (CarUpdateDto carUpdateDto){
        return Car.builder()
                .brand(carUpdateDto.getBrand())
                .model(carUpdateDto.getModel())
                .costPerHour(carUpdateDto.getCostPerHour())
                .licensePlate(carUpdateDto.getLicensePlate())
                .manufacturingYear(carUpdateDto.getManufacturingYear())
                .build();
    }
}
