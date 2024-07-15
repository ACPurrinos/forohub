package com.acpurrinos.forohub.repository;

import com.acpurrinos.forohub.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
UserDetails findByEmail(String username);



}
