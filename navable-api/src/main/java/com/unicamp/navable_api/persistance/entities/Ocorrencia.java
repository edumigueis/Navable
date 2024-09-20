package com.unicamp.navable_api.persistance.entities;

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

