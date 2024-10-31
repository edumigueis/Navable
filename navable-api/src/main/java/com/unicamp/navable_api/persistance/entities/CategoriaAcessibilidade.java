package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CategoriaAcessibilidade")
public class CategoriaAcessibilidade {
    @Id
    private Integer categoriaAcId;
    private String nome;
    private String grupo;
}