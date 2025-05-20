package com.notsblock.notsblock.Security.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NotaResponse {
    private String usuario;
    private String titulo;
    private String contenido;
}
