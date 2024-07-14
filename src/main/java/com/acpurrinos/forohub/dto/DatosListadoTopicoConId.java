package com.acpurrinos.forohub.dto;

import com.acpurrinos.forohub.models.Curso;
import com.acpurrinos.forohub.models.Estado;
import com.acpurrinos.forohub.models.Topico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;


public record DatosListadoTopicoConId(Long id, String titulo, String mensaje, Curso nombreCurso, Estado estado, Long idUsuario) {

    public DatosListadoTopicoConId(Topico topico){
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),topico.getNombreCurso(), topico.getEstado(), topico.getIdUsuario());
    };

}
