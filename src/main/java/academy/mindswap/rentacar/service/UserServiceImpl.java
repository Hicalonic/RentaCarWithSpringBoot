package academy.mindswap.rentacar.service;


import academy.mindswap.rentacar.converter.UserConverter;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.dto.UserUpdateDto;
import academy.mindswap.rentacar.dto.UserUpdateRoleDto;
import academy.mindswap.rentacar.exceptions.EmailException;
import academy.mindswap.rentacar.model.Role;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
       boolean hasEmail;
       hasEmail = userRepository.findAll().stream().anyMatch(u ->u.getEmail().equals(userCreateDto.getEmail()));
       if(hasEmail) {
           throw new EmailException("Email already exists!");
       }
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
        user.setPassword(userUpdateDto.getNewPassword());
        return userConverter.fromUserEntityToUserDto(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);

    }

    @Override
    public UserDto findUserByFirstName(String firstName) {
       User userFound = userRepository.findUserByFirstName(firstName);
       UserDto userFoundDto = userConverter.fromUserEntityToUserDto(userFound);
       return userFoundDto;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }



    @Override
    public UserDto updateRole(UserUpdateRoleDto userUpdateRoleDto) {
     User user =   userRepository.findByEmail(userUpdateRoleDto.getEmail()).orElseThrow( () -> new UsernameNotFoundException("user doesn't exist"));
     user.setRole(userUpdateRoleDto.getRole());
     userRepository.save(user);
        return userConverter.fromUserEntityToUserDto(user);
    }


}
