package com.unicamp.navable_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoDTO {
    private Integer idEstabelecimento;
    private Integer idTipoEstabeleci;
    private String nome;
    private Integer latitude;
    private Integer longitude;
    private String imagem;
    private String endereco;
}
