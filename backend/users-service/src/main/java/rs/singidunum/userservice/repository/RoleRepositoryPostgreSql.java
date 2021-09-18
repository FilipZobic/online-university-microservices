package rs.singidunum.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.model.Role;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepositoryPostgreSql extends JpaRepository <Role, UUID> {

  @Query("SELECT r FROM Role r WHERE r.title LIKE :title")
  Optional<Role> findByTitle(String title);
}
