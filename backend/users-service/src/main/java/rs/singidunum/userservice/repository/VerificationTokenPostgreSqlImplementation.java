package rs.singidunum.userservice.repository;

import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.exception.ResourceNotFoundException;
import rs.singidunum.userservice.model.VerificationToken;

import java.util.UUID;

@Repository
public class VerificationTokenPostgreSqlImplementation implements VerificationTokenRepository {

  private VerificationTokenPostgreSql verificationTokenPostgreSql;

  public VerificationTokenPostgreSqlImplementation(VerificationTokenPostgreSql verificationTokenPostgreSql) {
    this.verificationTokenPostgreSql = verificationTokenPostgreSql;
  }

  @Override
  public VerificationToken save(VerificationToken verificationToken) {
    return this.verificationTokenPostgreSql.save(verificationToken);
  }

  @Override
  public void delete(UUID id) {
    this.verificationTokenPostgreSql.deleteById(id);
  }

  @Override
  public VerificationToken findById(UUID id) throws ResourceNotFoundException {
    return this.verificationTokenPostgreSql.findById(id).orElseThrow(() -> new ResourceNotFoundException("Token does not exist"));
  }

  @Override
  public VerificationToken findByUserId(UUID id) {
    return this.verificationTokenPostgreSql.findByUserId(id);
  }
}
