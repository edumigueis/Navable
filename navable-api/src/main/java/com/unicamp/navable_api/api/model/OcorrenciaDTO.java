package com.unicamp.navable_api.api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OcorrenciaDTO {
    private Integer idOcorrencia;
    private Integer idUsuario;
    private Integer idTipoOcorrencia;
    private TipoOcorrenciaDTO tipoOcorrencia;
    private Integer latitude;
    private Integer longitude;
}

