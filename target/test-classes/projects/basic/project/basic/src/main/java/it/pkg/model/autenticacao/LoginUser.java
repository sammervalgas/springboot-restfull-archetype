package it.pkg.model.autenticacao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public @NoArgsConstructor @Data
class LoginUser implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String pass;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<LoginProfile> profiles = new ArrayList<>();

    public LoginUser(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return profiles;
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
