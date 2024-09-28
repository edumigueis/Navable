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
@Table(name = "TipoEstabelecimento")
public class TipoEstabelecimento {
    @Id
    private Integer idTipoEstabeleci;
    private String nome;
}
