package rs.singidunum.userservice.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import rs.singidunum.userservice.dto.AddressDto;
import rs.singidunum.userservice.dto.UserDto;
import rs.singidunum.userservice.model.Address;
import rs.singidunum.userservice.model.Privilege;
import rs.singidunum.userservice.model.Role;
import rs.singidunum.userservice.model.User;

import java.util.*;

public class Utility {
  public static UserDto userToDto(User user) {

    Address address = user.getAddress();
    AddressDto addressDto = null;
    if (address != null) {
      addressDto = AddressDto.builder()
        .addressValue(address.getAddress())
        .cityName(address.getCityName())
        .zipCode(address.getZipCode())
        .countryAlpha2Code(address.getCountryCode())
        .build();
    }

    Boolean isDeleted = null;

    if (UtilitySecurity.userHasAdminRole()) {
      isDeleted = user.getDeleted();
    }

    Set<String> grantedAuthorities = new HashSet<>();

    user.getRoles().forEach(role -> {
      grantedAuthorities.add(role.getTitle());
      role.getPrivileges().forEach(privilege -> {
        grantedAuthorities.add(privilege.getTitle());
      });
    });

    return UserDto.builder()
      .id(user.getId())
      .address(addressDto)
      .email(user.getEmail())
      .username(user.getUsername())
      .grantedAuthorities(grantedAuthorities)
      .isDeleted(isDeleted)
      .isEnabled(user.getEnabled())
      .build();
  }

  public static Collection<? extends GrantedAuthority> getAuthorities(
          Collection<Role> roles) {
    return getGrantedAuthorities(getPrivileges(roles));
  }

  public static List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    for (String privilege : privileges) {
      authorities.add(new SimpleGrantedAuthority(privilege));
    }
    return authorities;
  }

  public static List<String> getPrivileges(Collection<Role> roles) {

    List<String> privileges = new ArrayList<>();
    List<Privilege> collection = new ArrayList<>();
    for (Role role : roles) {
      privileges.add(role.getTitle());
      collection.addAll(role.getPrivileges());
    }
    for (Privilege item : collection) {
      privileges.add(item.getTitle());
    }
    return privileges;
  }
}
