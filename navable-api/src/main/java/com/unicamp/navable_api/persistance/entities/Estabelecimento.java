package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

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
    @Getter
    private Double latitude;
    private Double longitude;
    private String imagem;
    private String endereco;
    @Transient
    private Integer nota;
}
