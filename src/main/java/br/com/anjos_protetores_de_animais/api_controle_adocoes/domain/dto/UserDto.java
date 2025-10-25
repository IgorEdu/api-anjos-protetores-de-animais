package br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.entity.Adopter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private final String name;

    private final String email;

    private final String phone;

    private final String address;

    @Builder
    protected UserDto(final String name,
                      final String email,
                      final String phone,
                      final String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public static UserDto toDto(final Adopter entity) {
        return UserDto.builder()
            .name(entity.getName())
            .email(entity.getEmail())
            .phone(entity.getPhone())
            .address(entity.getAddress())
            .build();
    }
}
