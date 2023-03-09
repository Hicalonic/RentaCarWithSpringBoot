package academy.mindswap.rentacar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @GetMapping("/hello")
    public String getRental() {
        return "Hello from Rental!";
    }
}
