package com.notsblock.notsblock.Security.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NotaDTO {

    private String titulo;

    private String contenido;

    private Long usuarioId;
}
