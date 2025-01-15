package com.Foro.Hubapi.Alex.CONTROLLER;

import com.Foro.Hubapi.Alex.DATOS_DTO.USUARIO.*;
import com.Foro.Hubapi.Alex.REPOSITORIOS.USUARIORE;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class USUARIO {

    private final USUARIORE USUARIORE;

    public USUARIO(USUARIORE USUARIORE) {
        this.USUARIORE = USUARIORE;
    }

    @PostMapping
    public ResponseEntity<RESPUESTA_USUARIO1> registrar(@RequestBody REGISTRO_USUARIO datosRegistro, UriComponentsBuilder uri) {
        com.Foro.Hubapi.Alex.MODELOS.USUARIO usuario = USUARIORE.save(new com.Foro.Hubapi.Alex.MODELOS.USUARIO(datosRegistro));
        RESPUESTA_USUARIO1 datosRespuesta = new RESPUESTA_USUARIO1(usuario);
        URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<LISTADO_USUARIO>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(USUARIORE.findAll(paginacion).map(LISTADO_USUARIO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESPUESTA_USUARIO2> retornaDatos(@PathVariable Long id) {
        com.Foro.Hubapi.Alex.MODELOS.USUARIO usuario = USUARIORE.getReferenceById(id);
        return ResponseEntity.ok(new RESPUESTA_USUARIO2(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RESPUESTA_USUARIO1> actualizar(@RequestBody ACTUALIZAR_USUARIO datosActualizar) {
        com.Foro.Hubapi.Alex.MODELOS.USUARIO usuario = USUARIORE.getReferenceById(datosActualizar.id());
        usuario.actualizarDatos(datosActualizar);
        return ResponseEntity.ok( new RESPUESTA_USUARIO1(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        com.Foro.Hubapi.Alex.MODELOS.USUARIO usuario = USUARIORE.getReferenceById(id);
        USUARIORE.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
