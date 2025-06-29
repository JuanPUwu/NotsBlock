package com.notsblock.notsblock.Security.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class NotaResponse {
    private Long id;
    private String titulo;
    private String contenido;
    private Boolean enPapelera;
}
