package rs.singidunum.userservice.repository;

import rs.singidunum.userservice.model.Privilege;

import java.util.Optional;

public interface PrivilegeRepository {

  Optional<Privilege> findByTitle(String title);

  Privilege save(Privilege privilege);
}
