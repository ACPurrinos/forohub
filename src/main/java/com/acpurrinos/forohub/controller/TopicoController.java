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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

    /*@PostMapping
    public void registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        System.out.println("ESTADO 200 OK");
        topicoRepository.save(new Topico(datosRegistroTopico));}*/

    @PostMapping
    public ResponseEntity<Void> registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        try {
            topicoRepository.save(new Topico(datosRegistroTopico));
            return ResponseEntity.status(HttpStatus.CREATED).build(); // Devuelve 201 Created si se registró correctamente
        } catch (DataIntegrityViolationException e) {
            // Captura la excepción de violación de integridad de datos (puede ser por llave duplicada u otro error similar)
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Devuelve 409 Conflict si hay una llave duplicada u otro conflicto
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve 500 Internal Server Error para otros errores
        }
    }


    ;
    //@GetMApping
    //public List<Topico> listarTopicos() {return topicoRepository.findAll();}

    @GetMapping
    public Page<DatosListadoTopicos> listarTopicos(@PageableDefault(size=10) Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);}

   /* @GetMapping("/ultimos10")// Ruta relativa a la base
    public Page<DatosListadoTopicos> listarUltimosDiezTopicos() {
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(topicoRepository.findAll(pageable).map(DatosListadoTopicos::new));
        return topicoRepository.findAll(pageable).map(DatosListadoTopicos::new);
    }*/
   @GetMapping("/ultimos10") // Ruta relativa a la base
   public ResponseEntity<Page<DatosListadoTopicos>> listarUltimosDiezTopicos() {
       Pageable pageable = PageRequest.of(0, 10);
       Page<DatosListadoTopicos> pagina = topicoRepository.findAll(pageable).map(DatosListadoTopicos::new);

       if (!pagina.isEmpty()) {
           return ResponseEntity.ok(pagina);
       } else {
           return ResponseEntity.notFound().build();
       }
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


/*
    @PutMapping("/{id}")
    @Transactional
    public void actualizarTopico(@PathVariable long id, @RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.actualizarTopico(datosActualizacionTopico);
    }*/

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizacionTopico datosActualizacionTopico) {
        Optional<Topico> optionalTopico = topicoService.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();
            topico.actualizarTopico(datosActualizacionTopico);
            //topicoRepository.save(topico);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}") //este es un delete a nivel de bases de datos, no es un delete logico
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

    @PutMapping("/logico/{id}")
    @Transactional
    public ResponseEntity<Void> cerrarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoService.findById(id);
        if (optionalTopico.isPresent()) {
            Topico topico = optionalTopico.get();

            topico.cerrarTopico(); // Marca el tópico como inactivo (borrado lógico)
            topicoRepository.save(topico); // Guarda los cambios en la base de datos

            return ResponseEntity.noContent().build(); // Devuelve una respuesta 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // Si no se encuentra el tópico, devuelve 404 Not Found
        }
    }
}




