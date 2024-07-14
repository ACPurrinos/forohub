package com.acpurrinos.forohub.dto;

import com.acpurrinos.forohub.models.Curso;
import com.acpurrinos.forohub.models.Estado;
import com.acpurrinos.forohub.models.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(String titulo, String mensaje, LocalDateTime fechaDeCreacion,Curso nombreCurso,  Estado estado) {

public DatosListadoTopicos(Topico topico){
    this(topico.getTitulo(), topico.getMensaje(), topico.getFechaDeCreacion(),topico.getNombreCurso(), topico.getEstado());
};
}
