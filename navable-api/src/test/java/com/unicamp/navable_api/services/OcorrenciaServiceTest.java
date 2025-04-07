package com.unicamp.navable_api.services;

import com.unicamp.navable_api.api.model.OcorrenciaDTO;
import com.unicamp.navable_api.services.impl.OcorrenciaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OcorrenciaServiceTest {

    @Mock
    private OcorrenciaService ocorrenciaService;

    @Test
    void testCreateOcorrencia_mockeado() {
        // Arrange
        OcorrenciaDTO dtoEntrada = new OcorrenciaDTO();
        dtoEntrada.setIdOcorrencia(1);
        dtoEntrada.setIdUsuario(123);
        dtoEntrada.setIdTipoOcorrencia(10);
        dtoEntrada.setLatitude(-22.0);
        dtoEntrada.setLongitude(-47.0);

        OcorrenciaDTO dtoRetornado = new OcorrenciaDTO();
        dtoRetornado.setIdOcorrencia(1);
        dtoRetornado.setIdUsuario(123);
        dtoRetornado.setIdTipoOcorrencia(10);
        dtoRetornado.setLatitude(-22.0);
        dtoRetornado.setLongitude(-47.0);

        when(ocorrenciaService.createOcorrencia(dtoEntrada)).thenReturn(dtoRetornado);

        // Act
        OcorrenciaDTO resultado = ocorrenciaService.createOcorrencia(dtoEntrada);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.getIdOcorrencia());
        assertEquals(123, resultado.getIdUsuario());
        assertEquals(10, resultado.getIdTipoOcorrencia());
    }
}
