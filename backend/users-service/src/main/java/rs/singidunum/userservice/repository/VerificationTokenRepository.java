package rs.singidunum.userservice.repository;

import rs.singidunum.userservice.exception.ResourceNotFoundException;
import rs.singidunum.userservice.model.VerificationToken;

import java.util.UUID;

public interface VerificationTokenRepository {
  VerificationToken save(VerificationToken verificationToken);

  void delete(UUID id);

  VerificationToken findById(UUID id) throws ResourceNotFoundException;

  VerificationToken findByUserId(UUID id);
}
