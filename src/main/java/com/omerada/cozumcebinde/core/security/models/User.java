package com.omerada.cozumcebinde.core.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import com.omerada.cozumcebinde.core.domain.BaseModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseModel<UserDTO> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(unique = true)
  private String username;
  private String email;
  @Column(length = 10000)
  @JsonIgnore
  private String password;
  private Long tcKimlikNo;
  private String ad;
  private String soyad;
  private Date guncellemeZamani;
  private boolean aktif;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(  name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Long getTcKimlikNo() {
    return tcKimlikNo;
  }

  public void setTcKimlikNo(Long tcKimlikNo) {
    this.tcKimlikNo = tcKimlikNo;
  }

  public String getAd() {
    return ad;
  }

  public void setAd(String ad) {
    this.ad = ad;
  }

  public String getSoyad() {
    return soyad;
  }

  public void setSoyad(String soyad) {
    this.soyad = soyad;
  }

  public Date getGuncellemeZamani() {
    return guncellemeZamani;
  }

  public void setGuncellemeZamani(Date guncellemeZamani) {
    this.guncellemeZamani = guncellemeZamani;
  }

  public boolean isAktif() {
    return aktif;
  }

  public void setAktif(boolean aktif) {
    this.aktif = aktif;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public UserDTO toDTO() {
    return getModelMapper().map(this, UserDTO.class);
  }

}
