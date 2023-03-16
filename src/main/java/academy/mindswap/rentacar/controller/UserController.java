package academy.mindswap.rentacar.controller;




import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.dto.UserUpdateDto;
import academy.mindswap.rentacar.dto.UserUpdateRoleDto;
import academy.mindswap.rentacar.model.Role;
import academy.mindswap.rentacar.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto userCreateDto, BindingResult bindingResult) {
      if(bindingResult.hasErrors()) {

          List<FieldError> errors = bindingResult.getFieldErrors();

          for (FieldError error : errors) {
              System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
          }
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
      UserDto savedUser = userService.createUser(userCreateDto);
      return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping(path = "{createUsers}")
    public ResponseEntity<String> createUsers(@Valid @RequestBody List<UserCreateDto> userCreateDtoList, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userCreateDtoList.stream().forEach(c -> userService.createUser(c));

        return  new ResponseEntity<>("Ok",HttpStatus.OK);
    }



    @GetMapping(path = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/name/{firstName}")
    public ResponseEntity<UserDto> getUserByFirstName(@PathVariable("firstName") String firstName) {
        return new ResponseEntity<>(userService.findUserByFirstName(firstName), HttpStatus.OK);
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long id,@Valid @RequestBody UserUpdateDto userUpdateDto) {
        userUpdateDto.setId(id);
       userService.updateUser(userUpdateDto);
        return new ResponseEntity<>("update success", HttpStatus.OK);
    }

    @DeleteMapping(path = "{userID}")
    public ResponseEntity<String> deleteUser(@PathVariable("userID") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted",HttpStatus.OK);
    }

    @PutMapping(path = "/updaterole")
    public ResponseEntity<String> updateRole(@Valid @RequestBody UserUpdateRoleDto userUpdateRoleDto) {
        userService.updateRole(userUpdateRoleDto);
        return new ResponseEntity<>("User Role has been updated!",HttpStatus.OK);

    }




}
