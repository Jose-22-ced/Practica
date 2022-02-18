package com.es.practica.service;


import com.es.practica.models.user.Usuario;
import com.es.practica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private S3Service s3Service;


    public boolean guardarUnuario(Usuario usuarioRequest){
        try {
            usuarioRepository.save(usuarioRequest);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean actualzairUnuario(Usuario usuarioRequest){
        try {
            usuarioRepository.save(usuarioRequest);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public List<Usuario> verUsuarios(){
        return usuarioRepository.findAll()
                .stream()
                .peek(usuario -> usuario.setImagenUrl(s3Service.getObjectUrl(usuario.getImagenPath())))
                .peek(usuario -> usuario.setPdfUrl(s3Service.getObjectUrl(usuario.getPdfPath())))
                .collect(Collectors.toList());
    }
    public Usuario verUsuariosbyId(Long id){
        if (usuarioRepository.existsById(id)){
            return usuarioRepository.getById(id);
        }
        return null;
    }
    public boolean eliminarUsuariosbyId(Long id){
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
          return true;
        }
        return false;
    }

}
