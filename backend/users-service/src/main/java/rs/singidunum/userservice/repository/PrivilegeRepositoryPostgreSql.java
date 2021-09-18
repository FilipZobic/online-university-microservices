package rs.singidunum.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.model.Privilege;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrivilegeRepositoryPostgreSql extends JpaRepository <Privilege, UUID> {

  @Query("SELECT p FROM Privilege p WHERE p.title LIKE :title")
  Optional<Privilege> findByTitle(String title);
}
