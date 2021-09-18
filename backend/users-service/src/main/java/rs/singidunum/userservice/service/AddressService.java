package rs.singidunum.userservice.service;


import rs.singidunum.userservice.dto.AddressDto;
import rs.singidunum.userservice.model.Address;

import java.util.UUID;

public interface AddressService {

    Address save(AddressDto addressDto) throws Exception;

    Address update(UUID id, AddressDto addressDto, Address address) throws Exception;
}
