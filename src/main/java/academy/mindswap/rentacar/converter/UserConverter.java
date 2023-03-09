package academy.mindswap.rentacar.converter;

import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.dto.UserUpdateDto;
import academy.mindswap.rentacar.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto fromUserEntityToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public  User fromUserDtoToUserEntity(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }

    public User fromUserCreateDtoToEntity (UserCreateDto userCreateDto){
        return User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getEmail())
                .password(userCreateDto.getPassword())
                .role(userCreateDto.getRole())
                .build();
    }

    public User fromUserUpdaterDtoToEntity (UserUpdateDto userUpdateDto) {
        return User.builder()
                .firstName(userUpdateDto.getFirstName())
                .lastName(userUpdateDto.getLastName())
                .email(userUpdateDto.getEmail())
                .password(userUpdateDto.getNewPassword())
                .role(userUpdateDto.getRole())
                .build();
    }
}


