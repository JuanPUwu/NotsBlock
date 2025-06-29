package com.notsblock.notsblock.Service;

import com.notsblock.notsblock.Configuration.AESUtil;
import com.notsblock.notsblock.Model.Nota;
import com.notsblock.notsblock.Model.Usuario;
import com.notsblock.notsblock.Repository.NotaRepository;
import com.notsblock.notsblock.Repository.UsuarioRepository;
import com.notsblock.notsblock.Security.DTO.ActualizarNotaDTO;
import com.notsblock.notsblock.Security.DTO.NotaDTO;
import com.notsblock.notsblock.Security.response.NotaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotaService {

    private final NotaRepository notaRepository;

    private final UsuarioRepository usuarioRepository;

    @Autowired
    private AESUtil aesUtil;

    public NotaResponse crearNota(NotaDTO notaDTO) throws Exception {
        // Validar que al menos uno de los campos tenga contenido
        boolean tituloVacio = notaDTO.getTitulo() == null || notaDTO.getTitulo().trim().isEmpty();
        boolean contenidoVacio = notaDTO.getContenido() == null || notaDTO.getContenido().trim().isEmpty();

        if (tituloVacio && contenidoVacio) {
            throw new IllegalArgumentException("La nota debe tener al menos un título o contenido.");
        }

        // Obtener el ID del usuario autenticado desde el token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long usuarioId = Long.parseLong(authentication.getPrincipal().toString());

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new Exception("Usuario no encontrado con ID: " + usuarioId));

        Nota nota = new Nota();
        nota.setTitulo(aesUtil.encrypt(notaDTO.getTitulo()));
        nota.setContenido(aesUtil.encrypt(notaDTO.getContenido()));
        nota.setEnPapelera(false);
        nota.setUsuario(usuario);
        notaRepository.save(nota);

        NotaResponse notaResponse = new NotaResponse();
        notaResponse.setId(nota.getId());
        notaResponse.setTitulo(notaDTO.getTitulo());
        notaResponse.setContenido(notaDTO.getContenido());
        notaResponse.setEnPapelera(nota.getEnPapelera());

        return notaResponse;
    }

    public List<NotaResponse> notaByIdUsuario() throws Exception {
        try {
            // Obtener el ID del usuario autenticado desde el token
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long usuarioId = Long.parseLong(authentication.getPrincipal().toString());

            Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
            if (usuarioOpt.isEmpty()) {
                throw new Exception("Usuario no encontrado con ID: " + usuarioId);
            }

            List<Nota> notas = notaRepository.findByUsuario_id(usuarioId);
            List<NotaResponse> notaResponsesList = new ArrayList<>();

            for (Nota nota : notas) {
                NotaResponse notaResponse = new NotaResponse();
                notaResponse.setId(nota.getId());
                notaResponse.setTitulo(aesUtil.decrypt(nota.getTitulo()));
                notaResponse.setContenido(aesUtil.decrypt(nota.getContenido()));
                notaResponse.setEnPapelera(nota.getEnPapelera());
                notaResponsesList.add(notaResponse);
            }

            return notaResponsesList;

        } catch (Exception e) {
            throw new Exception("Error al buscar notas del usuario: " + e.getMessage(), e);
        }
    }


    public String actualizarNota(Long notaId, ActualizarNotaDTO dto) throws Exception {
        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new Exception("Nota no encontrada con ID: " + notaId));

        // Verificar que al menos uno de los dos campos tenga contenido útil
        boolean tituloValido = dto.getTitulo() != null && !dto.getTitulo().trim().isEmpty();
        boolean contenidoValido = dto.getContenido() != null && !dto.getContenido().trim().isEmpty();

        if (!tituloValido && !contenidoValido) {
            throw new IllegalArgumentException("Debes enviar al menos un título o contenido con texto.");
        }

        boolean hayCambios = false;

        if (dto.getTitulo() != null) {
            String tituloActual = aesUtil.decrypt(nota.getTitulo());
            if (!dto.getTitulo().equals(tituloActual)) {
                nota.setTitulo(aesUtil.encrypt(dto.getTitulo()));
                hayCambios = true;
            }
        }

        if (dto.getContenido() != null) {
            String contenidoActual = aesUtil.decrypt(nota.getContenido());
            if (!dto.getContenido().equals(contenidoActual)) {
                nota.setContenido(aesUtil.encrypt(dto.getContenido()));
                hayCambios = true;
            }
        }

        if (!hayCambios) {
            return "La nota ya contiene el mismo título y contenido. No se realizaron cambios.";
        }

        notaRepository.save(nota);
        return "Nota actualizada correctamente.";
    }


    public String actualizarEstado(Long notaId, Boolean enPapelera) throws Exception {
        // Verificar que el estado no sea nullo
        if (enPapelera == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo. Debe ser true o false.");
        }

        Nota nota = notaRepository.findById(notaId).orElseThrow(() -> new Exception("Nota no encontrada con ID: " + notaId));

        // Verificar si el estado ya es el mismo
        if (enPapelera.equals(nota.getEnPapelera())) {
            return "El estado ya se encuentra en " + enPapelera;
        }

        nota.setEnPapelera(enPapelera);

        notaRepository.save(nota);

        return "Estado de nota actualizado";
    }

    public String eliminarNota(Long notaId) throws Exception {
        Nota nota = notaRepository.findById(notaId)
                .orElseThrow(() -> new Exception("Nota no encontrada con ID: " + notaId));

        notaRepository.delete(nota);

        return "Nota eliminada correctamente";
    }

}
