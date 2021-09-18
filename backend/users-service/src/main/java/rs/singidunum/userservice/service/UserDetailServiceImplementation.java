package rs.singidunum.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.util.Utility;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {

  private final UserDetailsHelperService userService;

  public UserDetailServiceImplementation(UserDetailsHelperService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = this.userService.findUserByUsername(username).orElse(null);
    if (user == null) {
      return null;
    }
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getEnabled(),true,true,true, Utility.getAuthorities(user.getRoles()));
  }


}
