package org.andsav.family_budget_manager.model;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.andsav.family_budget_manager.model.abstractentity.NamedEntity;
import org.andsav.family_budget_manager.model.enums.Role;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author Andrii_Savka
 *
 */

@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "unique_email") })
@NamedQueries({ @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
    @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u WHERE u.email=?1"),
    @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u ORDER BY u.name, u.email") })
@AttributeOverride(name = "name", column = @Column(name = "user_name"))
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends NamedEntity {

  public static final String DELETE = "User.delete";
  public static final String ALL_SORTED = "User.getAllSorted";
  public static final String BY_EMAIL = "User.getByEmail";

  @Lob
  @Column(name = "user_icon")
  private Byte[] userIcon;

  @Column(nullable = false, unique = true)
  @Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
  private String email;

  @Column(nullable = false)
  @NotEmpty
  @Length(min = 5)
  private String password;

  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "users_roles", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
      "role" }), joinColumns = @JoinColumn(name = "user_id"))
  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "role")
  @BatchSize(size = 10)
  @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
  @JsonIgnore
  private Set<Role> roles;

  @Column(nullable = false)
  private boolean enabled = true;

  public User() {
  }

  public User(String name, Byte[] userIcon, String email, String accountPassword, Role... roles) {
    this(null, name, userIcon, email, accountPassword, roles);
  }

  public User(Integer id, String name, Byte[] userIcon, String email, String accountPassword, Role... roles) {
    super(id, name);
    Set<Role> set = new HashSet<>();
    Collections.addAll(set, roles);
    setRoles(set);
    this.userIcon = userIcon;
    this.email = email;
    this.password = accountPassword;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Collection<Role> roles) {
    this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
  }

  public Byte[] getUserIcon() {
    return userIcon;
  }

  public void setuserIcon(Byte[] userIcon) {
    this.userIcon = userIcon;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public String toString() {
    return "User [" + super.toString() + "email=" + email + ", enabled=" + enabled + ", roles=" + roles + "]";
  }

}
