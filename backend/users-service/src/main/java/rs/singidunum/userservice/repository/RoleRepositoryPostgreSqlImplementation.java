package rs.singidunum.userservice.repository;

import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.model.Role;

import java.util.Optional;

@Repository
public class RoleRepositoryPostgreSqlImplementation implements RoleRepository {

  private final RoleRepositoryPostgreSql roleRepositoryPostgreSql;

  public RoleRepositoryPostgreSqlImplementation(RoleRepositoryPostgreSql roleRepositoryPostgreSql) {
    this.roleRepositoryPostgreSql = roleRepositoryPostgreSql;
  }

  @Override
  public Optional<Role> findByTitle(String title) {
    return this.roleRepositoryPostgreSql.findByTitle(title);
  }

  @Override
  public Role save(Role role) {
    return this.roleRepositoryPostgreSql.save(role);
  }
}
