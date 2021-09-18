package rs.singidunum.userservice.service;

import org.springframework.stereotype.Service;
import rs.singidunum.userservice.model.Privilege;
import rs.singidunum.userservice.repository.PrivilegeRepository;
import rs.singidunum.userservice.repository.PrivilegeRepositoryPostgreSqlImplementation;

import java.util.Optional;

@Service
public class PrivilegeServiceImplementation implements PrivilegeService {

  private PrivilegeRepository privilegeRepository;

  public PrivilegeServiceImplementation(PrivilegeRepositoryPostgreSqlImplementation privilegeRepository) {
    this.privilegeRepository = privilegeRepository;
  }

  @Override
  public Privilege findOrCreateIfNotFound(String title) {
    Optional<Privilege> privilege = this.privilegeRepository.findByTitle(title);
    if (privilege.isPresent()) {
      return privilege.get();
    } else {
      return this.privilegeRepository.save(new Privilege(title));
    }
//    return this.privilegeRepository.findByTitle(title).orElse(this.privilegeRepository.save(new Privilege(title)));
  }
}
