package rs.singidunum.userservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.singidunum.userservice.dto.UserDto;
import rs.singidunum.userservice.dto.UserDtoUpdate;
import rs.singidunum.userservice.exception.BadPasswordException;
import rs.singidunum.userservice.exception.ResourceNotFoundException;
import rs.singidunum.userservice.exception.TokenExpiredException;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.model.VerificationToken;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

  User registerUser(UserDto user) throws Exception;

  User updateUser(UserDtoUpdate userDto, UUID id) throws Exception;

  void deleteUser(UUID id) throws Exception;

  User findUserById(UUID id) throws Exception;

  Optional<User> findUserByUsername(String username);

  Optional<User> findUserByEmail(String email);

  Iterable<User> findAllUsers();

  Page<User> findAllUsersPageable(Pageable pageable);

  User createUser(UserDto userDto) throws Exception;

  VerificationToken createVerificationToken(User user);

  void confirmUser(UUID token) throws ResourceNotFoundException, TokenExpiredException;

  void destroyOldAndCreateNewVerificationToken(UUID expiredToken) throws Exception;

  String createJwtToken(String username, String password) throws ResourceNotFoundException, BadPasswordException;
}
