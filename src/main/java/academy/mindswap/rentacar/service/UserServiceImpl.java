package academy.mindswap.rentacar.service;


import academy.mindswap.rentacar.converter.UserConverter;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.dto.UserUpdateDto;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

   private UserRepository userRepository;
   private UserConverter userConverter;


   @Autowired
   public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
       this.userRepository = userRepository;
       this.userConverter = userConverter;
   }


    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        if(!userCreateDto.getPassword().equals(userCreateDto.getRetypedPassword())) {
            throw new IllegalArgumentException(("Password does not match"));
        }
        User user = userConverter.fromUserCreateDtoToEntity(userCreateDto);
        user = userRepository.save(user);
        return userConverter.fromUserEntityToUserDto(user);
    }

    @Override
    public UserDto getUserById(Long userId) {
       User user = userRepository.getReferenceById(userId);
         return userConverter.fromUserEntityToUserDto(user);

    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users = userRepository.findAll();
       List<UserDto> usersDtos = users.stream()
               .map(user -> userConverter.fromUserEntityToUserDto(user))
               .toList();
       return usersDtos;
    }

    @Transactional
    @Override
    public UserDto updateUser(UserUpdateDto userUpdateDto) {
       String realPassword = userRepository.getReferenceById(userUpdateDto.getId()).getPassword();

        if (!userUpdateDto.getCurrentPassword().equals(realPassword)) {
            throw new IllegalArgumentException(("Password does not match"));
        }

        User user = userRepository.getReferenceById(userUpdateDto.getId());
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        user.setEmail(userUpdateDto.getEmail());
        user.setRole(userUpdateDto.getRole());
        user.setPassword(userUpdateDto.getNewPassword());
        return userConverter.fromUserEntityToUserDto(user);
    }



    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }
}
