package rs.singidunum.userservice.repository;

import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.model.Privilege;

import java.util.Optional;

@Repository
public class PrivilegeRepositoryPostgreSqlImplementation implements PrivilegeRepository{

  private final PrivilegeRepositoryPostgreSql privilegeRepositoryPostgreSql;

  public PrivilegeRepositoryPostgreSqlImplementation(PrivilegeRepositoryPostgreSql privilegeRepositoryPostgreSql) {
    this.privilegeRepositoryPostgreSql = privilegeRepositoryPostgreSql;
  }

  @Override
  public Optional<Privilege> findByTitle(String title) {
    return this.privilegeRepositoryPostgreSql.findByTitle(title);
  }

  @Override
  public Privilege save(Privilege privilege) {
    return this.privilegeRepositoryPostgreSql.save(privilege);
  }
}
