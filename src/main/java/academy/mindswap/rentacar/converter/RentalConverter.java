package academy.mindswap.rentacar.converter;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.model.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalConverter {

    public RentalDto fromCarEntityToCarDto(Rental rental) {
        return RentalDto.builder()
                .id(rental.getId())
                .rentalDate(rental.getRentalDate())
                .deliveryDate(rental.getDeliveryDate())
                .car(rental.getCar())
                .user(rental.getUser())
                .build();
    }

    public Rental fromCarDtoToEntity(RentalDto rentalDto) {
        return Rental.builder()
                .id(rentalDto.getId())
                .rentalDate(rentalDto.getRentalDate())
                .deliveryDate(rentalDto.getDeliveryDate())
                .car(rentalDto.getCar())
                .user(rentalDto.getUser())
                .build();
    }

}
