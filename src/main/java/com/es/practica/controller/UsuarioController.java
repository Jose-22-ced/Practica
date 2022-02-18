package com.es.practica.controller;


import com.es.practica.models.user.Usuario;
import com.es.practica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public ResponseEntity<Boolean> crearUsuario(@RequestBody Usuario usuarioRequest){
        System.out.printf(usuarioRequest.getEmail());
        if (usuarioService.guardarUnuario(usuarioRequest)){
            return new ResponseEntity(true,HttpStatus.CREATED);
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<Boolean> actualizarUsuario(@RequestBody Usuario usuarioRequest){
        System.out.printf(usuarioRequest.getEmail());
        if (usuarioService.actualzairUnuario(usuarioRequest)){
            return new ResponseEntity(true,HttpStatus.CREATED);
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> aliminarUsuariobyId(@PathVariable Long id){
        if (usuarioService.eliminarUsuariosbyId(id)){
            return new ResponseEntity(true,HttpStatus.CREATED);
        }
        return new ResponseEntity(false,HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<List<Usuario>> verUsuario(){
        List<Usuario> usuarioResponses = usuarioService.verUsuarios();
        return new ResponseEntity<List<Usuario>>(usuarioResponses,HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> verUsuarioById(@PathVariable Long id){
        Usuario usuarioResponses = usuarioService.verUsuariosbyId(id);
        return new ResponseEntity<Usuario>(usuarioResponses,HttpStatus.OK);
    }

}
