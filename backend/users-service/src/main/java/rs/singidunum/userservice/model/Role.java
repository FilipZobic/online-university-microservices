package rs.singidunum.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends GenericUuidModel{

  @Column(unique = true)
  private String title;

  @ManyToMany
  @JoinTable(
    name = "application_user_role",
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
  )
  private Collection<User> users;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "role_privilege",
    joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
  )
  private Collection<Privilege> privileges;

  public Role(String title, Collection<Privilege> privileges) {
    this.title = title;
    this.privileges = privileges;
  }
}
