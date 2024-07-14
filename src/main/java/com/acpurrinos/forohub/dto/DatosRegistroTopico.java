package com.acpurrinos.forohub.dto;

import com.acpurrinos.forohub.models.Curso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public record DatosRegistroTopico(
        @NotNull
                @Valid
        Long idUsuario,
        @NotBlank
                @Valid
        String titulo,
        @NotBlank
                @Valid
        String mensaje,
         @NotNull
                 @Valid
         Curso nombreCurso){}

//si se hace el trabajo con todas las tablas tendria que ser el id de la tabla cursos

