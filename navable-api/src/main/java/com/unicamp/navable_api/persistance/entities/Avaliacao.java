package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Avaliacao")
public class Avaliacao {
    @Id
    private Integer idAvaliacao;
    private Integer idUsuario;
    private Integer idEstabelecimento;
    private String avaliacao;
    private Integer nota;
    private LocalDate timestamp;
}

