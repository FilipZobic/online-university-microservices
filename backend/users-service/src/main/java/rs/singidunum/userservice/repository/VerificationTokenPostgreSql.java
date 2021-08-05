package rs.singidunum.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.model.VerificationToken;

import java.util.UUID;

@Repository
public interface VerificationTokenPostgreSql extends JpaRepository <VerificationToken, UUID> {
    @Query("SELECT vt FROM VerificationToken vt WHERE vt.user.id = :id")
    VerificationToken findByUserId(UUID id);
}
