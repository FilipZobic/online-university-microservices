package rs.singidunum.userservice.service;

import rs.singidunum.userservice.exception.ResourceNotFoundException;
import rs.singidunum.userservice.model.Privilege;
import rs.singidunum.userservice.model.Role;

import java.util.Collection;

public interface RoleService {

  Role findOrCreateIfNotFound(String title, Collection<Privilege> privileges);

  Role findByTitle(String title) throws ResourceNotFoundException;
}
