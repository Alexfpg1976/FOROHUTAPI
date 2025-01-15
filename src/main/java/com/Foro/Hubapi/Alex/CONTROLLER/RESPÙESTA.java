package com.Foro.Hubapi.Alex.CONTROLLER;

import com.Foro.Hubapi.Alex.DATOS_DTO.RESPUESTA.*;
import com.Foro.Hubapi.Alex.MODELOS.ESTADO;
import com.Foro.Hubapi.Alex.MODELOS.RESPUESTA;
import com.Foro.Hubapi.Alex.MODELOS.TOPICO;
import com.Foro.Hubapi.Alex.MODELOS.USUARIO;
import com.Foro.Hubapi.Alex.REPOSITORIOS.RESPUESTARE;
import com.Foro.Hubapi.Alex.REPOSITORIOS.TOPICORE;
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
@RequestMapping("/respuestas")
public class RESPÙESTA {

    private final RESPUESTARE RESPUESTARE;
    private final TOPICORE TOPICORE;
    private final USUARIORE USUARIORE;

    public RESPÙESTA(RESPUESTARE RESPUESTARE, TOPICORE TOPICORE, USUARIORE USUARIORE) {
        this.RESPUESTARE = RESPUESTARE;
        this.TOPICORE = TOPICORE;
        this.USUARIORE = USUARIORE;
    }

    @PostMapping
    public ResponseEntity<RETORNO_RESPUESTA1> registrar(@RequestBody REGISTRO_RESPUESTA datosRegistro, UriComponentsBuilder uri) {
        TOPICO topico = TOPICORE.getReferenceById(datosRegistro.topicoId());
        if (topico.getEstado() == ESTADO.CERRADO) {
           return ResponseEntity.unprocessableEntity().build();
        }

        USUARIO autor = USUARIORE.getReferenceById(datosRegistro.autorId());
        RESPUESTA respuesta = RESPUESTARE.save(new RESPUESTA(datosRegistro, topico, autor));
        topico.agregarRespuesta(respuesta);
        RETORNO_RESPUESTA1 datosRespuesta = new RETORNO_RESPUESTA1(respuesta);
        URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<LISTADO_RESPUESTA>> listar(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(RESPUESTARE.findAll(paginacion).map(LISTADO_RESPUESTA::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RETORNO_RESPUESTA2> retornaDatos(@PathVariable Long id) {
        RESPUESTA respuesta = RESPUESTARE.getReferenceById(id);
        return ResponseEntity.ok(new RETORNO_RESPUESTA2(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RETORNO_RESPUESTA1> actualizar(@RequestBody ACTUALIZAR_RESPUESTA datosActualizar) {
        RESPUESTA respuesta = RESPUESTARE.getReferenceById(datosActualizar.id());
        TOPICO topico = TOPICORE.getReferenceById(datosActualizar.topicoId());
        USUARIO autor = USUARIORE.getReferenceById(datosActualizar.autorId());
        respuesta.actualizarDatos(datosActualizar, topico, autor);
        return ResponseEntity.ok( new RETORNO_RESPUESTA1(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        RESPUESTA respuesta = RESPUESTARE.getReferenceById(id);
        RESPUESTARE.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
