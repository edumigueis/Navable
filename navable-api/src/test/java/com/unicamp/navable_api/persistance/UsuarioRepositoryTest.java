package com.unicamp.navable_api.persistance;

import com.unicamp.navable_api.persistance.entities.Usuario;
import com.unicamp.navable_api.persistance.repositories.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        Usuario usuario1 = new Usuario(1, "User 1", "user1@example.com", "password", 0);
        Usuario usuario2 = new Usuario(2, "User 2", "user2@example.com", "password", 0);

        usuarioRepository.saveAll(Arrays.asList(usuario1, usuario2));
    }

    @Test
    public void testFindByEmail() {
        String email = "user1@example.com";
        Optional<Usuario> result = usuarioRepository.findByEmail(email);
        assertThat(result).isPresent();
        assertThat(result.get().getNome()).isEqualTo("User 1");
    }
}