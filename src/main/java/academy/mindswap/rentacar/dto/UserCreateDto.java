package academy.mindswap.rentacar.dto;
import academy.mindswap.rentacar.model.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {
    @NotBlank(message = "Must have a first name")
    private String firstName;
    @NotBlank(message = "Must have a last name")

    private String lastName;

    @NotBlank(message = "Must have an email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email")
    private String email;
    @Size(max = 20, min = 2, message = "Password must be at least 8 characters long")
    @NotBlank(message = "Must have a password")
    private String password;

    @Size(max = 20, min = 2, message = "Password must be at least 8 characters long")
    @NotBlank(message = "Must have a password")
    private String retypedPassword;


}