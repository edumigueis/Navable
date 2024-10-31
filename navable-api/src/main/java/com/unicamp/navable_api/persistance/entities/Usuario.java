package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

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
