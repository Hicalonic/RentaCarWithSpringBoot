package academy.mindswap.rentacar.controller;

import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.dto.RentalDto;
import academy.mindswap.rentacar.service.RentalService;
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

      @GetMapping("")
        public ResponseEntity<List<RentalDto>> getRentals() {

        return new ResponseEntity<>(rentalService.getAllRentals(), HttpStatus.OK);
    }

    @GetMapping(path = "{rentalID}")
    public ResponseEntity<RentalDto> getRentalById(@PathVariable("rentalID") Long id) {
        return new ResponseEntity<>(rentalService.getRentalById(id), HttpStatus.OK);
    }

    @PutMapping(path = "{rentalID}")
    public ResponseEntity<RentalDto> updateRental(@PathVariable("rentalID") Long id,  @RequestBody RentalDto rentalDto) {
        rentalDto.setId(id);
        RentalDto rentalDto1 =  rentalService.updateRental(rentalDto);
        return new ResponseEntity<>(rentalDto1, HttpStatus.OK);
    }

    @DeleteMapping(path = "{rentalID}")
    public ResponseEntity<String> deleteRental(@PathVariable("rentalID") Long id) {
        rentalService.deleteRental(id);
        return new ResponseEntity<>("Rental has been deleted",HttpStatus.OK);
    }
}


