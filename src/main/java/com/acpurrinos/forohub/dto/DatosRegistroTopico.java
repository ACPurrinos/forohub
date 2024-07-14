package com.acpurrinos.forohub.dto;

import com.acpurrinos.forohub.models.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(

        @NotBlank

        Long idUsuario,
        @NotBlank
        String titulo,
        String mensaje,
        Curso nombreCurso) {
//si se hace el trabajo con todas las tablas tendria que ser el id de la tabla cursos
}
