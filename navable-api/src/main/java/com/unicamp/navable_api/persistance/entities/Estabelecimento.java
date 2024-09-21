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
@Table(name = "Estabelecimento")
public class Estabelecimento {
    @Id
    private Integer idEstabelecimento;
    private Integer idTipoEstabeleci;
    private String nome;
    private Integer latitude;
    private Integer longitude;
    private String imagem;
    private String endereco;
}
