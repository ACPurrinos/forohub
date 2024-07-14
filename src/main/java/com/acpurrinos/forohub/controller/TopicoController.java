package com.acpurrinos.forohub.controller;

import com.acpurrinos.forohub.dto.DatosListadoTopicos;
import com.acpurrinos.forohub.dto.DatosRegistroTopico;
import com.acpurrinos.forohub.models.Topico;
import com.acpurrinos.forohub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registraTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        System.out.println("ESTADO 200 OK");
        topicoRepository.save(new Topico(datosRegistroTopico));

    }

    ;

    //@GetMApping
    //public List<Topico> listarTopicos() {return topicoRepository.findAll();}
   /* public List<DatosListadoTopicos> listarTopicos(){
        return topicoRepository.findAll().stream().map(DatosListadoTopicos::new).toList();}*/
    @GetMapping
    public Page<DatosListadoTopicos> listarTopicos(Pageable paginacion){
        return topicoRepository.findAll(paginacion).map(DatosListadoTopicos::new);}


}
