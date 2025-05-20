package com.notsblock.notsblock.Controller;

import com.notsblock.notsblock.Security.DTO.NotaDTO;
import com.notsblock.notsblock.Security.DTO.NotaResponse;
import com.notsblock.notsblock.Service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nota")
@CrossOrigin("*")
public class NotaController {

    @Autowired
    public NotaService notaService;

    @PostMapping("/crear")
    public ResponseEntity<NotaResponse> CrearUsuario(@RequestBody NotaDTO notaDTO) throws Exception {
        NotaResponse nota = notaService.crearNota(notaDTO);
        return ResponseEntity.ok(nota);
    }
}
