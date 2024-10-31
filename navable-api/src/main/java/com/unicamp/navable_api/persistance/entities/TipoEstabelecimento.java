package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

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
