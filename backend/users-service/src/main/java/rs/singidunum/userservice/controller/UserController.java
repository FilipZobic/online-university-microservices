package rs.singidunum.userservice.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.singidunum.userservice.dto.UserDto;
import rs.singidunum.userservice.dto.UserDtoUpdate;
import rs.singidunum.userservice.event.OnRegistrationCompleteEvent;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.service.UserService;
import rs.singidunum.userservice.util.Utility;
import rs.singidunum.userservice.util.UtilitySecurity;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@Validated
@RequestMapping(path = "/api/users")
public class UserController {

  private UserService userService;

  private ApplicationEventPublisher eventPublisher;

  public UserController(UserService userService, ApplicationEventPublisher eventPublisher) {
    this.userService = userService;
    this.eventPublisher = eventPublisher;
  }

  @GetMapping(path = "/page")
  public ResponseEntity<?> getUsersPage() {
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> getUsers() {
    List<UserDto> userDtos = new ArrayList<>();
    this.userService.findAllUsers().forEach(user -> {
    userDtos.add(Utility.userToDto(user));
    });
    return new ResponseEntity<>(userDtos, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getUser(@PathVariable(name = "id")UUID id) throws Exception {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = this.userService.findUserById(id);
    // Maybe put this logic in findUser
    if (!user.getUsername().equals(SecurityContextHolder.getContext().getAuthentication().getName()) && !UtilitySecurity.userHasAdminRole()) {
      return new ResponseEntity<>("User doesn't have the permissions to request another users information", HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>(Utility.userToDto(user), HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto, HttpServletRequest request) throws Exception {
    String password = userDto.getPassword();
    User user = this.userService.createUser(userDto);
    this.eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, password, Locale.ENGLISH, request.getContextPath()));

    return new ResponseEntity<>(Utility.userToDto(user), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateUser(@PathVariable(name = "id") UUID id, @Valid @RequestBody UserDtoUpdate userDto) throws Exception {
    return new ResponseEntity<>(Utility.userToDto(this.userService.updateUser(userDto, id)), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable(name = "id") UUID id) throws Exception {
    this.userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

//  @PutMapping("/{id}/restore")
//  public ResponseEntity<?> restoreUser(@PathVariable(name = "id") UUID id) {
//    this.userService.deleteUser(id);
//    return new ResponseEntity<>(HttpStatus.OK);
//  }
}
