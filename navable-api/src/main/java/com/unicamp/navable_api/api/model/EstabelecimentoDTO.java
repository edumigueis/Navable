package com.unicamp.navable_api.api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoDTO {
    private Integer idEstabelecimento;
    private Integer idTipoEstabeleci;
    private String nome;
    private Double latitude;
    private Double longitude;
    private String imagem;
    private String endereco;
}
