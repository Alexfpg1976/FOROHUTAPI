package com.Foro.Hubapi.Alex.REPOSITORIOS;

import com.Foro.Hubapi.Alex.MODELOS.USUARIO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface USUARIORE extends JpaRepository<USUARIO, Long> {
    UserDetails findByEmail(String username);
}
