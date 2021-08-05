package rs.singidunum.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.singidunum.userservice.dto.CountryDto;
import rs.singidunum.userservice.service.CountryService;
import rs.singidunum.userservice.service.CountryServiceImplementation;

import java.util.List;

@RestController
public class CountryController {

    private CountryService countryService;

    public CountryController(CountryServiceImplementation countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/api/countries")
    public ResponseEntity<List<CountryDto>> getAllCountries () throws Exception {
        return new ResponseEntity<>(countryService.requestAllCountries(), HttpStatus.OK);
    }
}
