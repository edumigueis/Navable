package com.unicamp.navable_api.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Usuario")
public class Usuario {
    @Id
    private Integer idUsuario;
    private String nome;
    private String email;
    private String senha;
    private Integer pontos;
}
