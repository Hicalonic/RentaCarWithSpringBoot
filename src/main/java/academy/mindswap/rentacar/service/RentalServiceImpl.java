package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.RentalConverter;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RentalServiceImpl implements RentalService {

    private RentalConverter rentalConverter;
    private RentalRepository rentalRepository;
    @Autowired
    public RentalServiceImpl(RentalConverter rentalConverter, RentalRepository rentalRepository) {
        this.rentalConverter = rentalConverter;
        this.rentalRepository = rentalRepository;
    }

    @Override
    public RentalDto createRental(RentalDto rentalDto) {

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
