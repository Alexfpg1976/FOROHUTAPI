package com.Foro.Hubapi.Alex.CONTROLLER;

import com.Foro.Hubapi.Alex.DATOS_DTO.SEGURIDAD_TOKEN.JWTTOKEN;
import com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO.AUTENTICACION_USUARIO;
import com.Foro.Hubapi.Alex.MODELOS.USUARIO;
import com.Foro.Hubapi.Alex.SEGURIDAD.SERVICIO_TOKEN;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AUTENTICACION {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SERVICIO_TOKEN SERVICIOTOKEN;


    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid AUTENTICACION_USUARIO datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.email(), datosAutenticacion.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = SERVICIOTOKEN.generarToken((USUARIO) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTOKEN(JWTtoken));
    }
}
