package com.unicamp.navable_api.persistance.entities;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UsuarioCategoria")
public class UsuarioCategoria {
    @Id
    private Integer categoriaAcId;
    private Integer idUsuario;
}