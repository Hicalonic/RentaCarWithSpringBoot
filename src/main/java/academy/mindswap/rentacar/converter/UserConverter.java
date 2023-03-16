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
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public  User fromUserDtoToUserEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
    }

    public User fromUserCreateDtoToEntity (UserCreateDto userCreateDto){
        return User.builder()
                .firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getEmail())
                .password(userCreateDto.getPassword())
                .build();
    }

    public User fromUserUpdaterDtoToEntity (UserUpdateDto userUpdateDto) {
        return User.builder()
                .firstName(userUpdateDto.getFirstName())
                .lastName(userUpdateDto.getLastName())
                .email(userUpdateDto.getEmail())
                .password(userUpdateDto.getNewPassword())
                .build();
    }
}


