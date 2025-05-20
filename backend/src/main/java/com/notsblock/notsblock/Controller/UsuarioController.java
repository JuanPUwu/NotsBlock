package com.notsblock.notsblock.Controller;

import com.notsblock.notsblock.Model.Usuario;
import com.notsblock.notsblock.Security.DTO.UsuarioDTO;
import com.notsblock.notsblock.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
public class UsuarioController {
    @Autowired
    public UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> CrearUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioService.crearUsuario(usuarioDTO);
        return ResponseEntity.ok(usuario);
    }
}
