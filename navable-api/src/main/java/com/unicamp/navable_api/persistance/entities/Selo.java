package com.unicamp.navable_api.persistance.entities;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Selo")
public class Selo {
    @Id
    private Integer idSelo;
    private String nome;
    private String imagem;
}
