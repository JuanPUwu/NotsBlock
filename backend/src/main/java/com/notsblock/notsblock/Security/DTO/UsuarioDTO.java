package com.notsblock.notsblock.Security.DTO;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UsuarioDTO {
    private String username;

    private String email;

    private String password;
}
