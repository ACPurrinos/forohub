package com.acpurrinos.forohub.models;

import com.acpurrinos.forohub.dto.DatosActualizacionTopico;
import com.acpurrinos.forohub.dto.DatosListadoTopicoConId;
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
@Column(name="id_usuario")
private Long idUsuario;

@Column(name="fecha_creacion")
private LocalDateTime fechaDeCreacion= LocalDateTime.now();
@Enumerated(EnumType.STRING)
private Estado estado = Estado.ABIERTO;
@Enumerated(EnumType.STRING)
@Column(name="nombre_curso")
private Curso nombreCurso;

    public Topico(DatosRegistroTopico datosRegistroTopico /*,Curso curso*/) {
        this.idUsuario= datosRegistroTopico.idUsuario();
        this.nombreCurso=datosRegistroTopico.nombreCurso();
        this.titulo=datosRegistroTopico.titulo();
        this.mensaje= datosRegistroTopico.mensaje();
        //this.nombreCurso=curso;
    }

    public void actualizarTopico(DatosActualizacionTopico datosActualizacionTopico) {
        if(datosActualizacionTopico.nombreCurso() != null){
        this.nombreCurso=datosActualizacionTopico.nombreCurso();}
        if(datosActualizacionTopico.titulo() !=null){
        this.titulo= datosActualizacionTopico.titulo();}
        if(datosActualizacionTopico.mensaje() !=null){
        this.mensaje= datosActualizacionTopico.mensaje();}
        if(datosActualizacionTopico.idUsuario() !=null){
        this.idUsuario= datosActualizacionTopico.idUsuario();}
    }}



