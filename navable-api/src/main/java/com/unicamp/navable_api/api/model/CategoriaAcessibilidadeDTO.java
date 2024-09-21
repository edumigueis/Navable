package com.unicamp.navable_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaAcessibilidadeDTO {
    private Integer categoriaAcId;
    private String nome;
    private String grupo;
}