package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    private Role role;

    public Adopter(final String name,
                   final String email,
                   final String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
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
        this.role = Role.USER;
    }

    public Adopter(final UUID id) {
        super(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
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
