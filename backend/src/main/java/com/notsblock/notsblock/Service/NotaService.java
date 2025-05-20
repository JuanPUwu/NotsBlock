package com.notsblock.notsblock.Service;

import com.notsblock.notsblock.Configuration.AESUtil;
import com.notsblock.notsblock.Model.Nota;
import com.notsblock.notsblock.Model.Usuario;
import com.notsblock.notsblock.Repository.NotaRepository;
import com.notsblock.notsblock.Repository.UsuarioRepository;
import com.notsblock.notsblock.Security.DTO.NotaDTO;
import com.notsblock.notsblock.Security.DTO.NotaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;
    private final UsuarioRepository usuarioRepository;

    public NotaResponse crearNota(NotaDTO notaDTO) throws Exception {
        Usuario usuario = usuarioRepository.findById(notaDTO.getUsuarioId()).orElseThrow();
        Nota nota = new Nota();
        nota.setTitulo(AESUtil.encrypt(notaDTO.getTitulo()));
        nota.setContenido(AESUtil.encrypt(notaDTO.getContenido()));
        nota.setUsuario(usuario);
        notaRepository.save(nota);
        NotaResponse notaResponse = new NotaResponse();
        notaResponse.setTitulo(notaDTO.getTitulo());
        notaResponse.setContenido(notaDTO.getContenido());
        notaResponse.setUsuario(usuario.getUsername());
        return notaResponse;
    }
}
