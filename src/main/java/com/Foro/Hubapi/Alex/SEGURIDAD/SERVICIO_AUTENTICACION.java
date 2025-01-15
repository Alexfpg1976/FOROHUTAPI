package com.Foro.Hubapi.Alex.SEGURIDAD;

import com.Foro.Hubapi.Alex.REPOSITORIOS.USUARIORE;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SERVICIO_AUTENTICACION implements UserDetailsService {

    private final USUARIORE USUARIORE;

    SERVICIO_AUTENTICACION(USUARIORE USUARIORE) {
        this.USUARIORE = USUARIORE;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return USUARIORE.findByEmail(username);
    }
}
