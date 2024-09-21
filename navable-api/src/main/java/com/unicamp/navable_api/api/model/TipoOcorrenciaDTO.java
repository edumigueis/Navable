package com.unicamp.navable_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoOcorrenciaDTO {
    private Integer idTipoOcorrencia;
    private String nome;
    private String icone;
}