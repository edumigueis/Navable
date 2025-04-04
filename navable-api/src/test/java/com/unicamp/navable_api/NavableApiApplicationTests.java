package com.unicamp.navable_api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NavableApiApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Hikari pool must start for this, meaning docker container must be up
    }

    @Test
    void testServiceBeanExists() {
        assertThat(applicationContext.containsBean("avaliacaoService")).isTrue();
        assertThat(applicationContext.containsBean("estabelecimentoService")).isTrue();
        assertThat(applicationContext.containsBean("ocorrenciaService")).isTrue();
        assertThat(applicationContext.containsBean("categoriaAcessibilidadeService")).isTrue();
    }

    @Test
    void testRepositoryBeanExists() {
        assertThat(applicationContext.containsBean("avaliacaoRepository")).isTrue();
        assertThat(applicationContext.containsBean("estabelecimentoRepository")).isTrue();
        assertThat(applicationContext.containsBean("ocorrenciaRepository")).isTrue();
        assertThat(applicationContext.containsBean("categoriaAcessibilidadeRepository")).isTrue();
    }

    @Test
    void testControllerBeanExists() {
        assertThat(applicationContext.containsBean("avaliacaoControllerImpl")).isTrue();
        assertThat(applicationContext.containsBean("estabelecimentoControllerImpl")).isTrue();
        assertThat(applicationContext.containsBean("ocorrenciaControllerImpl")).isTrue();
        assertThat(applicationContext.containsBean("categoriaAcessibilidadeControllerImpl")).isTrue();
    }
}