package com.notsblock.notsblock.Controller;

import com.notsblock.notsblock.Security.DTO.ActualizarNotaDTO;
import com.notsblock.notsblock.Security.DTO.NotaDTO;
import com.notsblock.notsblock.Security.response.NotaResponse;
import com.notsblock.notsblock.Service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user")
    public ResponseEntity<List<NotaResponse>> NotaByIdUsuario() throws Exception {
        List<NotaResponse> notaByUsuario = notaService.notaByIdUsuario();
        return ResponseEntity.ok(notaByUsuario);
    }

    @PatchMapping("/{id}/update")
    public ResponseEntity<String> actualizarNota(
            @PathVariable Long id,
            @RequestBody ActualizarNotaDTO dto
    ) throws Exception {
        String mensaje = notaService.actualizarNota(id, dto);
        return ResponseEntity.ok(mensaje);
    }

    @PatchMapping("/{id}/update/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long id, @RequestParam Boolean enPapelera) throws Exception {
        String mensaje = notaService.actualizarEstado(id, enPapelera);
        return ResponseEntity.ok(mensaje);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> eliminarNota(@PathVariable Long id) throws Exception {
        String mensaje = notaService.eliminarNota(id);
        return ResponseEntity.ok(mensaje);
    }
}
