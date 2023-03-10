package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.RentalConverter;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.CarUpdateDto;
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
        Rental rental = rentalRepository.getReferenceById(rentalId);
        return rentalConverter.fromRentalEntityToRentalDto(rental);

    }

    @Override
    public List<RentalDto> getAllRentals() {
        List<Rental> rentalsList = rentalRepository.findAll();
        List<RentalDto> rentalDtos = rentalsList.stream()
                .map(rental -> rentalConverter.fromRentalEntityToRentalDto(rental))
                .toList();
         return  rentalDtos;
    }
    @Transactional
    @Override
    public RentalDto updateRental(RentalDto rentalDto) {
//        User user = userRepository.getReferenceById(rentalDto.getUserId());
//        Car car = carRepository.getReferenceById(rentalDto.getCarId());

        //TODO : fazer update nas listas de rentals do carro e user
       // carRepository.findAll().stream().filter(c -> c.getRentalList().stream().filter(r -> r.getId().equals(rentalDto.getId())).isParallel());
          Rental rental = rentalRepository.getReferenceById(rentalDto.getId());
//        car.getRentalList().add(rental);
//        rental.setUser(user);
//        rental.setCar(car);
        rental.setRentalDate(rentalDto.getRentalDate());
        rental.setDeliveryDate(rentalDto.getDeliveryDate());
        rentalRepository.save(rental);
            return rentalConverter.fromRentalEntityToRentalDto(rental);
        }



    @Transactional
    @Override
    public void deleteRental(Long rentalId) {
        Rental rental = rentalRepository.getReferenceById(rentalId);
        Car car = rental.getCar();
        User user = rental.getUser();

        // remove rental from car's rental list
        car.getRentalList().remove(rental);
        user.getRentalList().remove(rental);
        carRepository.save(car);
        userRepository.save(user);
        //TODO : How to make delete foreign keys
        // delete rental entity
        rentalRepository.deleteById(rentalId);
    }
}
