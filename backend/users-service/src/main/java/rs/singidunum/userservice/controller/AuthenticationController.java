package rs.singidunum.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rs.singidunum.userservice.dto.UserDto;
import rs.singidunum.userservice.dto.UserLoginDto;
import rs.singidunum.userservice.event.OnRegistrationCompleteEvent;
import rs.singidunum.userservice.exception.BadPasswordException;
import rs.singidunum.userservice.exception.ResourceNotFoundException;
import rs.singidunum.userservice.exception.TokenExpiredException;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.service.UserService;
import rs.singidunum.userservice.service.UserServiceImplementation;
import rs.singidunum.userservice.util.TokenUtils;
import rs.singidunum.userservice.util.Utility;
import rs.singidunum.userservice.util.UtilitySecurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Validated
public class AuthenticationController {

  private UserService userService;

  private UtilitySecurity utilitySecurity;

  private ApplicationEventPublisher eventPublisher;

  @Autowired
  private TokenUtils tokenUtils;

  @Autowired
  public AuthenticationController(UserServiceImplementation userService, UtilitySecurity utilitySecurity, ApplicationEventPublisher eventPublisher) {
    this.userService = userService;
    this.utilitySecurity = utilitySecurity;
    this.eventPublisher = eventPublisher;
  }

  @PostMapping(path = "api/login")
  public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) throws BadPasswordException, ResourceNotFoundException {
    User user = userService.findUserByUsername(userLoginDto.getUsername()).orElse(null);
    String token = this.tokenUtils.generateToken(user);

    return new ResponseEntity<>(token, HttpStatus.OK);
  }

  @PostMapping(path = "api/registrationConfirmation")
  public ResponseEntity<?> confirmUser(@RequestParam(required = false) UUID token, HttpServletRequest request) throws ResourceNotFoundException, TokenExpiredException {
    this.userService.confirmUser(token);
    Map<String, String> responseMessage = new HashMap<>();
    responseMessage.put("body", "User has been successfully verified.");
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);
  }

  @PostMapping(path = "api/users/{id}/requestNewConfirmation")
  public ResponseEntity<?> reSendToken(@PathVariable(name = "id") UUID userId, HttpServletRequest request) throws Exception {
    this.userService.destroyOldAndCreateNewVerificationToken(userId);
    Map<String, String> responseMessage = new HashMap<>();
    responseMessage.put("body", "New token sent successfully");
    return new ResponseEntity<>(responseMessage, HttpStatus.OK);
  }
}
