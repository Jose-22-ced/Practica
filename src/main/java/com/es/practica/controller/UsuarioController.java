package com.es.practica.controller;


import com.es.practica.dto.UsuarioRequest;
import com.es.practica.dto.UsuarioResponse;
import com.es.practica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Boolean> crearUsuario(@RequestBody UsuarioRequest usuarioRequest){
        System.out.printf(usuarioRequest.getEmail());
        if (usuarioService.guardarUnuario(usuarioRequest)){
            return new ResponseEntity(true,HttpStatus.CREATED);
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<Boolean> actualizarUsuario(@RequestBody UsuarioRequest usuarioRequest){
        System.out.printf(usuarioRequest.getEmail());
        if (usuarioService.actualzairUnuario(usuarioRequest)){
            return new ResponseEntity(true,HttpStatus.CREATED);
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> verUsuario(){
        List<UsuarioResponse> usuarioResponses = usuarioService.verUsuarios();
        return new ResponseEntity<List<UsuarioResponse>>(usuarioResponses,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UsuarioResponse> verUsuarioById(@PathVariable Long id){
        UsuarioResponse usuarioResponses = usuarioService.verUsuariosbyId(id);
        return new ResponseEntity<UsuarioResponse>(usuarioResponses,HttpStatus.OK);
    }
}
