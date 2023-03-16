package academy.mindswap.rentacar.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping(path = "/user/client/employee/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
    return ResponseEntity.ok(service.register(request));
  }


  @PostMapping(path = "/user/client/employee/registerall")
  public ResponseEntity<String> registerAll(@RequestBody List<RegisterRequest> requestList) {
    requestList.stream().forEach(r -> service.register(r));
    return ResponseEntity.ok("All Clients have been registered!");
  }





  @PostMapping("/auth/admin")
  public ResponseEntity<AuthenticationResponse> registerAdmin(
          @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(service.registerAdmin(request));
  }


  @PostMapping("/auth/refresh")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(service.authenticate(request));
  }


}
