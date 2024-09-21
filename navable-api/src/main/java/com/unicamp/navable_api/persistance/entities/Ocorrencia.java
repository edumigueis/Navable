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
@Table(name = "Ocorrência")
public class Ocorrencia {
    @Id
    private Integer idOcorrencia;
    private Integer idUsuario;
    private Integer idTipoOcorrencia;
    private Integer latitude;
    private Integer longitude;
}

