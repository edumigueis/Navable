package com.unicamp.navable_api.api.model;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Estabelecimento")
public class Estabelecimento {
    @Id
    private Integer idEstabelecimento;
    private Integer idTipoEstabeleci;
    private String nome;
    private Integer latitude;
    private Integer longitude;
    private String imagem;
    private String endereco;
}
