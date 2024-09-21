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
@Table(name = "CategoriaAceTipoEstab")
public class CategoriaAceTipoEstab {
    @Id
    private Integer idTipoEstabeleci;
    private Integer categoriaAcId;
}