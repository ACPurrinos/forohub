package com.acpurrinos.forohub.service;

import com.acpurrinos.forohub.models.Topico;
import com.acpurrinos.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;



    @Service
    public class TopicoService {

        @Autowired
        private TopicoRepository topicoRepository;

        public Optional<Topico> findById(Long id) {
            return topicoRepository.findById(id);
        }
    }

