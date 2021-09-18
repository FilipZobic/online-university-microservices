package rs.singidunum.userservice.service;

import rs.singidunum.userservice.model.Privilege;

public interface PrivilegeService {

  Privilege findOrCreateIfNotFound(String title);
}
