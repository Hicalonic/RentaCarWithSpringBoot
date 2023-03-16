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
public class UserDtoUpdateRole {
    @NotBlank(message = "Must have an email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
    private String email;


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Must have a role")
    private Role role;

}