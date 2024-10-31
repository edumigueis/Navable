package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

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