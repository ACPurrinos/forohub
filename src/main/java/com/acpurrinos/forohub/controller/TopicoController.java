package com.acpurrinos.forohub.controller;

import com.acpurrinos.forohub.dto.DatosRegistroTopico;
import com.acpurrinos.forohub.models.Topico;
import com.acpurrinos.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;

@PostMapping
    public void registraTopico(@RequestBody DatosRegistroTopico datosRegistroTopico){
        System.out.println("ESTADO 200 OK");
        topicoRepository.save(new Topico(datosRegistroTopico));


    };
}
