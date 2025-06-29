package com.notsblock.notsblock.Repository;

import com.notsblock.notsblock.Model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    // Busqueda por usuario_id
    List<Nota> findByUsuario_id(Long usuario_id);
}
