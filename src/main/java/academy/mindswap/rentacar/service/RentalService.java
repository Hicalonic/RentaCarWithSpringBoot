package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.CarUpdateDto;
import academy.mindswap.rentacar.dto.RentalDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RentalService  {


     RentalDto createRental(RentalDto rentalDto);

    RentalDto getRentalById(Long rentalId);

    List<RentalDto> getAllRentals();

    RentalDto updateRental(RentalDto rentalDto);

    void deleteRental(Long rentalId);

    List<CarDto> getAvailableCars();
}

