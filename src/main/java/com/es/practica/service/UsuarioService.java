package com.es.practica.service;

import com.es.practica.dto.UsuarioRequest;
import com.es.practica.dto.UsuarioResponse;
import com.es.practica.models.user.Usuario;
import com.es.practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public boolean guardarUnuario(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setClave(usuarioRequest.getClave());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setEstado(usuarioRequest.isEstado());
        System.out.println(usuario);
        try {
            usuarioRepository.save(usuario);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean actualzairUnuario(UsuarioRequest usuarioRequest){
        Usuario usuario = new Usuario();
        usuario.setId(usuarioRequest.getId());
        usuario.setNombre(usuarioRequest.getNombre());
        usuario.setClave(usuarioRequest.getClave());
        usuario.setEmail(usuarioRequest.getEmail());
        usuario.setEstado(usuarioRequest.isEstado());
        System.out.println(usuario);
        try {
            usuarioRepository.save(usuario);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<UsuarioResponse> verUsuarios(){
        List<UsuarioResponse> usuarioResponseList = new ArrayList<>();
        usuarioRepository.findAll().forEach(usuario1 -> {
            UsuarioResponse usuarioResponse = new UsuarioResponse();
            usuarioResponse.setId(usuario1.getId());
            usuarioResponse.setNombre(usuario1.getNombre());
            usuarioResponse.setClave(usuario1.getClave());
            usuarioResponse.setEmail(usuario1.getEmail());
            usuarioResponse.setEstado(usuario1.isEstado());
            usuarioResponseList.add(usuarioResponse);
        });
        return usuarioResponseList;
    }
    public UsuarioResponse verUsuariosbyId(Long id){
        if (usuarioRepository.existsById(id)){
            UsuarioResponse usuarioResponse = new UsuarioResponse();
            Usuario usuario = usuarioRepository.getById(id);
            usuarioResponse.setId(usuario.getId());
            usuarioResponse.setNombre(usuario.getNombre());
            usuarioResponse.setClave(usuario.getClave());
            usuarioResponse.setEmail(usuario.getEmail());
            usuarioResponse.setEstado(usuario.isEstado());
            return usuarioResponse;
        }
        return null;
    }

}
