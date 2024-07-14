package com.acpurrinos.forohub.controller;

import com.acpurrinos.forohub.dto.DatosActualizacionTopico;
import com.acpurrinos.forohub.dto.DatosListadoTopicoConId;
import com.acpurrinos.forohub.dto.DatosListadoTopicos;
import com.acpurrinos.forohub.dto.DatosRegistroTopico;
import com.acpurrinos.forohub.models.Curso;
import com.acpurrinos.forohub.models.Topico;
import com.acpurrinos.forohub.repository.TopicoRepository;
import com.acpurrinos.forohub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public void registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        System.out.println("ESTADO 200 OK");
        topicoRepository.save(new Topico(datosRegistroTopico));}
    ;
    //@GetMApping
    //public List<Topico> listarTopicos() {return topicoRepository.findAll();}

    @GetMapping
    public Page<DatosListadoTopicos> listarTopicos(@PageableDefault(size=10) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);}

    @GetMapping("/ultimos10")// Ruta relativa a la base
    public Page<DatosListadoTopicos> listarUltimosDiezTopicos() {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(topicoRepository.findAll(pageable).map(DatosListadoTopicos::new));
        return topicoRepository.findAll(pageable).map(DatosListadoTopicos::new);
    }

    @GetMapping("/buscar")
    public Page<DatosListadoTopicos> buscarTopicos(
            @RequestParam(required = false) String nombreCurso,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Curso curso = Curso.valueOf(nombreCurso.toUpperCase()); // Convierte el nombre del curso a mayúsculas para que coincida con el enum
        return topicoRepository.buscarTopicos(curso, pageable).map(DatosListadoTopicos::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> getTopicoById(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.findById(id);
        if (topico.isPresent()) {
            return ResponseEntity.ok(topico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/conId")
    public Page<DatosListadoTopicoConId> listarTopicosConId(@PageableDefault(size=10) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosListadoTopicoConId::new);}

    //hacer el put con id dinamico
 /* @PutMapping("/{id}")
    @Transactional
    public void actualizarTopico(@PathVariable long id, @RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico) {
        Topico topico = topicoRepository.getReferenceById(datosActualizacionTopico.id());
        topico.actualizarTopico(datosActualizacionTopico);
    }*/

    @PutMapping("/{id}")
    @Transactional
    public void actualizarTopico(@PathVariable long id, @RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datosActualizacionTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoService.findById(id);
        if (optionalTopico.isPresent()) {
            topicoRepository.deleteById(id); // Elimina el tópico por su ID
            return ResponseEntity.noContent().build(); // Devuelve una respuesta 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra el tópico, devuelve 404 Not Found
        }
    }






}

