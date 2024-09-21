package com.unicamp.navable_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeloDTO {
    private Integer idSelo;
    private String nome;
    private String imagem;
}
