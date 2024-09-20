package com.unicamp.navable_api.persistance.entities;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Categoria_de_Acessibilidade")
public class CategoriaDeAcessibilidade {
    @Id
    private Integer categoriaAcId;
    private String nome;
    private String grupo;
}