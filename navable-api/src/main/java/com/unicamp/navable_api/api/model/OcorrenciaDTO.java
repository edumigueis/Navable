package com.unicamp.navable_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaDTO {
    private Integer idOcorrencia;
    private Integer idUsuario;
    private Integer idTipoOcorrencia;
    private Integer latitude;
    private Integer longitude;
}

