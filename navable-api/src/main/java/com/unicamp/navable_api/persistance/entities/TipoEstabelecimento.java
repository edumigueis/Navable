package com.unicamp.navable_api.persistance.entities;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Tipo_de_Estabelecimento")
public class TipoEstabelecimento {
    @Id
    private Integer idTipoEstabeleci;
    private String nome;
}
