package com.unicamp.navable_api.api.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {
    private Integer idAvaliacao;
    private Integer idUsuario;
    private Integer idEstabelecimento;
    private String avaliacao;
    private Integer nota;
    private LocalDate timestamp;
}

