package com.unicamp.navable_api.api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoOcorrenciaDTO {
    private Integer idTipoOcorrencia;
    private String nome;
    private String icone;
}