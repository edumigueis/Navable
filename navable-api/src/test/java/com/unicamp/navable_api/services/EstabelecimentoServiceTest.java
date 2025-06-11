package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.EstabelecimentoDTO;
import com.unicamp.navable_api.persistance.entities.Estabelecimento;
import com.unicamp.navable_api.services.impl.EstabelecimentoService;
import com.unicamp.navable_api.services.mappers.EstabelecimentoMapper;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EstabelecimentoServiceTest {

    private EstabelecimentoService service;

    @BeforeEach
    public void setup() {
        service = new EstabelecimentoService();
    }

    @Test
    public void testMapToDTO() {
        Estabelecimento est1 = new Estabelecimento();
        est1.setIdEstabelecimento(1);
        est1.setIdTipoEstabeleci(2);
        est1.setNome("Estabelecimento A");
        est1.setLatitude(10.0);
        est1.setLongitude(20.0);
        est1.setImagem("imagemA.jpg");
        est1.setEndereco("Rua A");

        Estabelecimento est2 = new Estabelecimento();
        est2.setIdEstabelecimento(2);
        est2.setIdTipoEstabeleci(3);
        est2.setNome("Estabelecimento B");
        est2.setLatitude(15.0);
        est2.setLongitude(25.0);
        est2.setImagem("imagemB.jpg");
        est2.setEndereco("Rua B");

        List<Estabelecimento> estabelecimentos = Arrays.asList(est1, est2);
        EstabelecimentoMapper mapper = EstabelecimentoMapper.INSTANCE;
        List<EstabelecimentoDTO> dtos = estabelecimentos.stream()
                .map(mapper::toDTO)
                .toList();

        assertEquals(2, dtos.size());

        EstabelecimentoDTO dto1 = dtos.get(0);
        EstabelecimentoDTO dto2 = dtos.get(1);

        assertEquals(dto1.getIdEstabelecimento(), est1.getIdEstabelecimento());
        assertEquals(dto2.getIdEstabelecimento(), est2.getIdEstabelecimento());
    }
}
