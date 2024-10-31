package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Ocorrencia")
public class Ocorrencia {
    @Id
    private Integer idOcorrencia;
    private Integer idUsuario;
    private Integer idTipoOcorrencia;
    private Integer latitude;
    private Integer longitude;
}

