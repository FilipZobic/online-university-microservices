package rs.singidunum.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.singidunum.userservice.model.Address;

import java.util.UUID;

@Repository
public interface AddressRepositoryPostgreSql extends JpaRepository<Address, UUID> {
}
