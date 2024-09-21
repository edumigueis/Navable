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
@Table(name = "Tipo_de_Ocorrência")
public class TipoOcorrencia {
    @Id
    private Integer idTipoOcorrencia;
    private String nome;
    private String icone;
}