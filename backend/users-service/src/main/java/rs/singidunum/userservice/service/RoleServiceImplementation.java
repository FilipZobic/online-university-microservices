package rs.singidunum.userservice.service;

import org.springframework.stereotype.Service;
import rs.singidunum.userservice.exception.ResourceNotFoundException;
import rs.singidunum.userservice.model.Privilege;
import rs.singidunum.userservice.model.Role;
import rs.singidunum.userservice.repository.RoleRepository;
import rs.singidunum.userservice.repository.RoleRepositoryPostgreSqlImplementation;

import java.util.Collection;
import java.util.Optional;


@Service
public class RoleServiceImplementation implements RoleService{

  private final RoleRepository roleRepository;

  public RoleServiceImplementation(RoleRepositoryPostgreSqlImplementation roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public Role findOrCreateIfNotFound(String title, Collection<Privilege> privileges) {

    Optional<Role> role = this.roleRepository.findByTitle(title);
    System.out.println();
    if (role.isPresent()) {
      return role.get();
    } else {
      return this.roleRepository.save(new Role(title, privileges));
    }

//    return this.roleRepository.findByTitle(title).orElse(this.roleRepository.save(new Role(title, privileges)));
  }

  @Override
  public Role findByTitle(String title) throws ResourceNotFoundException {
    return this.roleRepository.findByTitle(title).orElseThrow(() -> new ResourceNotFoundException("Role not found"));
  }
}
