package com.unicamp.navable_api.persistance.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Selo")
public class Selo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSelo;
    private String nome;
    private String imagem;
}
