package rs.singidunum.userservice.repository;

import rs.singidunum.userservice.model.Role;

import java.util.Optional;


public interface RoleRepository {

  Optional<Role> findByTitle(String title);
  Role save(Role role);
}
