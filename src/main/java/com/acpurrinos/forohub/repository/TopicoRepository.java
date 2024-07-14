package com.acpurrinos.forohub.repository;

import com.acpurrinos.forohub.models.Curso;
import com.acpurrinos.forohub.models.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository  extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t WHERE (:nombreCurso IS NULL OR t.nombreCurso = :nombreCurso)")
    Page<Topico> buscarTopicos(
            @Param("nombreCurso") Curso nombreCurso,
            Pageable pageable
    );
}
