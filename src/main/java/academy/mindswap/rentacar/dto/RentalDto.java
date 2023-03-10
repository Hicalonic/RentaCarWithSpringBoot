package academy.mindswap.rentacar.dto;

import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {

    @NotNull
    private Long id;
    @NotNull
    private LocalDate rentalDate;
    @NotNull
    private LocalDate deliveryDate;
    @NotNull
    private Long carId;
    @NotNull
    private Long userId;
}
