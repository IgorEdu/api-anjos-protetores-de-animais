package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

@Table(name = "adopters")
@Entity(name = "Adopter")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adopter extends AbstractBaseEntity implements UserDetails {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Boolean isDeleted = false;

    public Adopter(final String name,
                   final String email,
                   final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Adopter(final String name,
                   final String email,
                   final String password,
                   final String phone,
                   final String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Adopter(final UUID id) {
        super(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
