package academy.mindswap.rentacar.demo;

import academy.mindswap.rentacar.dto.UserDtoUpdateRole;
import academy.mindswap.rentacar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  UserService userService;

  public DemoController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @PutMapping(path = "/updaterole")
  public ResponseEntity<String> updateUserRole(@Valid @RequestBody UserDtoUpdateRole userDtoUpdateRole) {
    System.out.println("User controller  - updateUserRole()");
    userService.updateRole(userDtoUpdateRole);
    return new ResponseEntity<>("User role has been updated", HttpStatus.OK);
  }

}
