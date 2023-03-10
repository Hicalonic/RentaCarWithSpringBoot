package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.dto.UserCreateDto;
import academy.mindswap.rentacar.dto.UserDto;
import academy.mindswap.rentacar.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
public class RentalController {


    private RentalService rentalService;
    @Autowired
    public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
    }


    @PostMapping
    public ResponseEntity<RentalDto> createRental( @RequestBody RentalDto rentalDto, BindingResult bindingResult) {
        System.out.println(rentalDto.getId());
        System.out.println(rentalDto.getUserId());
        System.out.println(rentalDto.getCarId());
        if(bindingResult.hasErrors()) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                System.out.println(error.getObjectName() + " - " + error.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        RentalDto savedRentalDto = rentalService.createRental(rentalDto);

        return new ResponseEntity<>(savedRentalDto, HttpStatus.OK);
    }
}


