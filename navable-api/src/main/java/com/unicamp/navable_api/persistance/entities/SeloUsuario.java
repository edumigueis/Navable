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
@Table(name = "SeloUsuario")
public class SeloUsuario {
    @Id
    private Integer idUsuario;
    private Integer idSelo;
    private Date timestamp;
}

