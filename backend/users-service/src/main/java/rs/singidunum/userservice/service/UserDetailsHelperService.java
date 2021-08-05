package rs.singidunum.userservice.service;

import rs.singidunum.userservice.model.User;

import java.util.Optional;

public interface UserDetailsHelperService {
  public Optional<User> findUserByUsername(String user);
}
