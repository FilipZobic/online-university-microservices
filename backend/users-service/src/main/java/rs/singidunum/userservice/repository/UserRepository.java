package rs.singidunum.userservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.singidunum.userservice.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

  User registerUser(User user);

  User updateUser(User user, UUID id);

  void deleteUser(UUID id);

  Optional<User> findUserById(UUID id);

  Optional<User> findUserByUsername(String username);

  Optional<User> findUserByEmail(String email);

  Iterable<User> findAllUsers();

  Page<User> findAllUsersPageable(Pageable pageable);
}
