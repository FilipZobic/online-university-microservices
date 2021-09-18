package rs.singidunum.userservice.service;

import rs.singidunum.userservice.exception.CountryNotFoundException;
import rs.singidunum.userservice.dto.CountryDto;

import java.util.List;

public interface CountryService {
  List<CountryDto> requestAllCountries() throws Exception;

  CountryDto requestCountry(String alpha2code) throws CountryNotFoundException;

  String getUpdatedCountryCode(String oldCountryCode, String newCountryCode);
}
