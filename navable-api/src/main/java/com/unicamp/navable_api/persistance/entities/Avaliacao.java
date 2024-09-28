package com.unicamp.navable_api.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

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
    private Date timestamp;
}

