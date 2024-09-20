package com.unicamp.navable_api.persistance.entities;

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

