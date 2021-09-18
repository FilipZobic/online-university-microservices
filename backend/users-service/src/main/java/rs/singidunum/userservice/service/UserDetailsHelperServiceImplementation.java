package rs.singidunum.userservice.service;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import rs.singidunum.userservice.model.User;
import rs.singidunum.userservice.repository.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsHelperServiceImplementation implements UserDetailsHelperService {
  private UserRepository userRepository;

  private EntityManager entityManager;

  public UserDetailsHelperServiceImplementation(UserRepository userRepository, EntityManager entityManager) {
    this.userRepository = userRepository;
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public Optional<User> findUserByUsername(String user) {
    Session session = entityManager.unwrap(Session.class);
    Filter filter = session.enableFilter("deletedUserFilter");
    filter.setParameter("isDeleted", false);
    Optional<User> foundUser = this.userRepository.findUserByUsername(user);
    session.disableFilter("deletedUserFilter");
    return foundUser;
  }
}
