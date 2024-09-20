package com.unicamp.navable_api.persistance.entities;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AvaliacaoCategoria")
public class AvaliacaoCategoria {
    @Id
    private Integer categoriaAcId;
    private Integer idAvaliacao;
}

