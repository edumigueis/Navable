package com.unicamp.navable_api.persistance.entities;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Votos")
public class Votos {
    @Id
    private Integer idUsuario;
    private Integer idOcorrencia;
}