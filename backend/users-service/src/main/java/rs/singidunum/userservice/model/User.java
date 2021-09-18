package rs.singidunum.userservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "application_user")
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE application_user SET deleted = true WHERE id=?")
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
@Filter(name = "deletedUserFilter", condition = "deleted = :isDeleted")
//@Table(name = "application_user", uniqueConstraints = {
//  @UniqueConstraint(name = "UC_email", columnNames = { "email" } ),
//  @UniqueConstraint(name = "UC_username", columnNames =  { "username" } ) })

public class User extends GenericUuidModel {

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false, columnDefinition = "boolean default false")
  private Boolean deleted = false;

  @Column(nullable = false, columnDefinition = "boolean default false")
  private Boolean enabled = false;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
  @JoinTable(
    name = "application_user_role",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  private Collection<Role> roles;

  @OneToOne(optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id")
  private Address address = null;

  public User(UUID id, String username, String password, String email, Collection<Role> roles) {
    super(id);
    this.username = username;
    this.password = password;
    this.email = email;
    this.roles = roles;
  }

  public User(String username, String password, String email, Collection<Role>  roles, Address address, Boolean isEnabled) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.roles = roles;
    this.address = address;
    this.enabled = isEnabled;
  }
}
