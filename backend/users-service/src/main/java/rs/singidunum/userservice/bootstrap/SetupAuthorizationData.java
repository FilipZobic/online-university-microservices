package rs.singidunum.userservice.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rs.singidunum.userservice.dto.UserDto;
import rs.singidunum.userservice.model.Privilege;
import rs.singidunum.userservice.model.Role;
import rs.singidunum.userservice.service.*;

import java.util.Arrays;
import java.util.List;

@Component
public class SetupAuthorizationData implements ApplicationListener<ContextRefreshedEvent> {

  boolean alreadySetup = false;

  private final PrivilegeService privilegeService;

  private final RoleService roleService;

  private final UserService userService;

  public SetupAuthorizationData(PrivilegeServiceImplementation privilegeService, RoleServiceImplementation roleService, UserServiceImplementation userService) {
    this.privilegeService = privilegeService;
    this.roleService = roleService;
    this.userService = userService;
  }

  private void setupAuthorization() {
    Privilege readAllUserDataPrivilege = this.privilegeService.findOrCreateIfNotFound("READ_ALL_USER_DATA_PRIVILEGE");
    Privilege writeAllUserDataPrivilege = this.privilegeService.findOrCreateIfNotFound("MODIFY_ALL_USER_DATA_PRIVILEGE");

    Privilege readSelfUserDataPrivilege = this.privilegeService.findOrCreateIfNotFound("READ_SELF_USER_DATA_PRIVILEGE");
    Privilege writeSelfUserDataPrivilege = this.privilegeService.findOrCreateIfNotFound("MODIFY_SELF_USER_DATA_PRIVILEGE");

    List<Privilege> adminPrivileges = Arrays.asList(readAllUserDataPrivilege, writeAllUserDataPrivilege);
    Role roleAdmin = this.roleService.findOrCreateIfNotFound("ROLE_ADMIN", adminPrivileges);

    List<Privilege> userPrivileges = Arrays.asList(readAllUserDataPrivilege, writeSelfUserDataPrivilege);
    Role roleUser = this.roleService.findOrCreateIfNotFound("ROLE_USER", userPrivileges);

    List<Privilege> moderatorPrivileges = Arrays.asList(readAllUserDataPrivilege);
    Role moderator = this.roleService.findOrCreateIfNotFound("ROLE_MODERATOR", moderatorPrivileges);
  }

  private void setupUserData() {
    try {
      UserDto admin = new UserDto(null,"admin","admin","admin@gmail.com","ROLE_ADMIN",null,false,true,null,null);
      this.userService.createUser(admin);
    } catch (Exception ignored) {}
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    if (alreadySetup) {
      return;
    }
    this.setupAuthorization();
    this.setupUserData();
    alreadySetup = true;
  }
}
