package academy.mindswap.rentacar.dto;

import academy.mindswap.rentacar.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull
    private Long id;
    @NotBlank(message = "Must have a first name")
    private String firstName;
    @NotBlank(message = "Must have a last name")
    private String lastName;
    @NotBlank(message = "Must have an email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email doesn't seem to be correct")
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
