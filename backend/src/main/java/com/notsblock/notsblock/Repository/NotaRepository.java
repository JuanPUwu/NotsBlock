package com.notsblock.notsblock.Repository;

import com.notsblock.notsblock.Model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

}
