package com.unicamp.navable_api.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCategoriaDTO {
    private Integer categoriaAcId;
    private Integer idUsuario;
}