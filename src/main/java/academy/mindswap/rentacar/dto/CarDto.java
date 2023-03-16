package academy.mindswap.rentacar.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    @NotBlank(message = "Must have a brand name")
    private String brand;
    @NotBlank(message = "Must have a model name")
    private String model;
    @NotNull(message = "Must have a valid cost")
    private Integer costPerHour;
    @Size(max = 6, min = 6, message = "Invalid license plate")
    @NotBlank()
    private String licensePlate;
    @NotNull(message = "ManufacturingYear should be valid")
    private Integer manufacturingYear;

}