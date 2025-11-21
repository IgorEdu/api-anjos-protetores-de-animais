package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.User;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private final UUID id;

    private final String name;

    private final String email;

    private final String phone;

    private final String address;

    private final Role role;

    @Builder
    protected UserDto(final UUID id,
                      final String name,
                      final String email,
                      final String phone,
                      final String address,
                      final Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }

    public static UserDto toDto(final User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .role(entity.getRole())
                .build();
    }
}
