package academy.mindswap.rentacar.converter;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter {

    public RentalDto fromRentalEntityToRentalDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .rentalDate(rental.getRentalDate())
                .deliveryDate(rental.getDeliveryDate())
                .carId(rental.getCar().getId())
                .userId(rental.getUser().getId())
                .build();
    }

    public Rental fromRentalDtoToEntity(RentalDto rentalDto) {
        return Rental.builder()
                .id(rentalDto.getId())
                .rentalDate(rentalDto.getRentalDate())
                .deliveryDate(rentalDto.getDeliveryDate())
                .build();
    }
}
