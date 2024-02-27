package com.alex.carbider.CarBider.entities.user;

import com.alex.carbider.CarBider.entities.user.cars.CarEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CarEntity> carsList;

    @Size(min = 1, max = 64, message = "Username cannot be empty or more than 64 characters!")
    private String username;
    @Size(min = 4, max = 64, message = "Password cannot be weaker than 4 or more than 64 characters!")
    private String password;
    private int points;
    private GrantedAuthority authority;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean accountEnabled;
    private boolean credentialsNonExpired;

    @Enumerated(EnumType.STRING)
    private Roles role;



    public UserEntity() {}



    public UserEntity(String username, Roles roles, List<CarEntity> carsList, int points, String password, boolean accountNonExpired, boolean accountNonLocked, boolean accountEnabled, boolean credentialsNonExpired) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.accountEnabled = accountEnabled;
        this.credentialsNonExpired = credentialsNonExpired;
        this.carsList = carsList;
        this.points = points;
        this.role = roles;
    }



    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<CarEntity> getCarsList() {
        return carsList;
    }

    public void setCarsList(List<CarEntity> carsList) {
        this.carsList = carsList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return accountEnabled;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthority(GrantedAuthority authority) {
        this.authority = authority;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setAccountEnabled(boolean accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    public long getId() {
        return id;
    }
}