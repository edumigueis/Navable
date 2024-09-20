package com.unicamp.navable_api.persistance.entities;

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