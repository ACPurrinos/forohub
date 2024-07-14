package com.acpurrinos.forohub.dto;

import com.acpurrinos.forohub.models.Curso;
import com.acpurrinos.forohub.models.Estado;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(


        Long idUsuario,
        @NotNull
        Long id,
        String titulo,

        String mensaje,

        Estado estado,

        Curso nombreCurso)

{
}
