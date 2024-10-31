package com.unicamp.navable_api.api.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaAcessibilidadeDTO {
    private Integer categoriaAcId;
    private String nome;
    private String grupo;
}