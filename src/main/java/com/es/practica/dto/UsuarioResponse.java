package com.es.practica.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class UsuarioResponse implements Serializable {

    private Long id;

    private String nombre;

    private String clave;

    private String email;

    private boolean estado;
}
