package com.Foro.Hubapi.Alex.CONTROLLER;

import com.Foro.Hubapi.Alex.DATOS_DTO.TOPICO.*;
import com.Foro.Hubapi.Alex.MODELOS.CURSO;
import com.Foro.Hubapi.Alex.MODELOS.USUARIO;
import com.Foro.Hubapi.Alex.REPOSITORIOS.CURSORE;
import com.Foro.Hubapi.Alex.REPOSITORIOS.TOPICORE;
import com.Foro.Hubapi.Alex.REPOSITORIOS.USUARIORE;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TOPICO {

    private final TOPICORE TOPICORE;
    private final USUARIORE USUARIORE;
    private final CURSORE CURSORE;

    public TOPICO(TOPICORE TOPICORE, USUARIORE USUARIORE, CURSORE CURSORE) {
        this.TOPICORE = TOPICORE;
        this.USUARIORE = USUARIORE;
        this.CURSORE = CURSORE;
    }

    @PostMapping
    public ResponseEntity<RESPUESTA_TOPICO1> registrar(@RequestBody @Valid REGISTRO_TOPICO datosRegistro, UriComponentsBuilder uri) {
        USUARIO autor = USUARIORE.getReferenceById(datosRegistro.autorId());
        CURSO curso = CURSORE.getReferenceById(datosRegistro.cursoId());
        com.Foro.Hubapi.Alex.MODELOS.TOPICO topico = TOPICORE.save(new com.Foro.Hubapi.Alex.MODELOS.TOPICO(datosRegistro, autor, curso));
        RESPUESTA_TOPICO1 datosRespuesta = new RESPUESTA_TOPICO1(topico);
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<LISTADO_TOPICO>> listar(@PageableDefault(size = 10)Pageable paginacion) {
        return ResponseEntity.ok(TOPICORE.findAll(paginacion).map(LISTADO_TOPICO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RESPUESTA_TOPICO2> retornaDatos(@PathVariable Long id) {
        com.Foro.Hubapi.Alex.MODELOS.TOPICO topico = TOPICORE.getReferenceById(id);
        return ResponseEntity.ok(new RESPUESTA_TOPICO2(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RESPUESTA_TOPICO1> actualizar(@RequestBody @Valid ACTUALIZAR_TOPICO datosActualizar) {
        USUARIO autor = USUARIORE.getReferenceById(datosActualizar.autorId());
        CURSO curso = CURSORE.getReferenceById(datosActualizar.cursoId());
        com.Foro.Hubapi.Alex.MODELOS.TOPICO topico = TOPICORE.getReferenceById(datosActualizar.id());
        topico.actualizarDatos(datosActualizar, autor, curso);
        return ResponseEntity.ok( new RESPUESTA_TOPICO1(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        com.Foro.Hubapi.Alex.MODELOS.TOPICO topico = TOPICORE.getReferenceById(id);
        //TOPICORE.delete(topico);
        topico.cerrarTopico();
        return ResponseEntity.noContent().build();
    }
}
