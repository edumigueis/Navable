package com.unicamp.navable_api.api.model;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Avaliação")
public class Avaliacao {
    @Id
    private Integer idAvaliacao;
    private Integer idUsuario;
    private Integer idEstabelecimento;
    private String avaliacao;
    private Integer nota;
    private Date timestamp;
}

