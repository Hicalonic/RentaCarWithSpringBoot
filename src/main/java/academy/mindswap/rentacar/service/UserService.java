package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.dto.UserUpdateDto;
import academy.mindswap.rentacar.model.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserCreateDto userCreateDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserUpdateDto userUpdateDto);

    void deleteUser(Long userId);

    UserDto findUserByFirstName(String firstName);
    User findUserByEmail(String email);
}
