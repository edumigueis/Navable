package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TipoOcorrencia")
public class TipoOcorrencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoOcorrencia;
    private String nome;
    private String icone;
}