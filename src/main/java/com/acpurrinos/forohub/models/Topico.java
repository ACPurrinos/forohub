package com.acpurrinos.forohub.models;

import com.acpurrinos.forohub.dto.DatosRegistroTopico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name="Topico")
@Getter  //anotacion de Lombok que va a generar los getters
@NoArgsConstructor //Anot. de lombock que genera constructor vacio
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

private Long id;

private String titulo;
private String mensaje;

private Long idUsuario;

@Column(name="fecha_creacion")
private LocalDateTime fechaDeCreacion= LocalDateTime.now();
@Enumerated(EnumType.STRING)
private Estado estado = Estado.ABIERTO;
@Enumerated(EnumType.STRING)
private Curso nombreCurso;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.idUsuario= datosRegistroTopico.idUsuario();
        this.nombreCurso=datosRegistroTopico.nombreCurso();
        this.titulo=datosRegistroTopico.titulo();
        this.mensaje= datosRegistroTopico.mensaje();
    }
}

