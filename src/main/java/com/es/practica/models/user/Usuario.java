package com.es.practica.models.user;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="alumnos")
@Entity
@Data
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    private String clave;

    private String email;

    private boolean estado;



}
