package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.RentalConverter;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.Rental;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.CarRepository;
import academy.mindswap.rentacar.repository.RentalRepository;
import academy.mindswap.rentacar.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RentalServiceImpl implements RentalService {

    private RentalConverter rentalConverter;
    private RentalRepository rentalRepository;

    private CarRepository carRepository;
    private UserRepository userRepository;
    @Autowired
    public RentalServiceImpl(RentalConverter rentalConverter, RentalRepository rentalRepository,UserRepository userRepository,CarRepository carRepository) {
        this.rentalConverter = rentalConverter;
        this.rentalRepository = rentalRepository;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public RentalDto createRental(RentalDto rentalDto) {

//        carRepository.findOne(rentalDto.getCar).orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + rentalDto.getCarId()));)
        carRepository.findById(rentalDto.getCarId()).orElseThrow(() -> new EntityNotFoundException("Car not found with id: " + rentalDto.getCarId()));
        userRepository.findById(rentalDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + rentalDto.getUserId()));

        Rental rental = rentalConverter.fromRentalDtoToEntity(rentalDto);
        Car carChoosen = carRepository.getReferenceById(rentalDto.getCarId());
        User userChoosen = userRepository.getReferenceById(rentalDto.getUserId());
        rental.setCar(carChoosen);
        rental.setUser(userChoosen);
        carChoosen.getRentalList().add(rental);
        userChoosen.getRentalList().add(rental);

        rentalRepository.save(rental);

       return rentalConverter.fromRentalEntityToRentalDto(rental);
    }

    @Override
    public RentalDto getRentalById(Long rentalId) {
        return null;
    }

    @Override
    public List<RentalDto> getAllRentals() {
        return null;
    }

    @Override
    public RentalDto updateRental(RentalDto rentalDto) {
        return null;
    }

    @Override
    public void deleteRental(Long rentalId) {

    }
}
