package com.unicamp.navable_api.services.filters;
import com.unicamp.navable_api.persistance.entities.Avaliacao;

public interface FiltroAvaliacaoStrategy {
    /**
     * Retorna true se a avaliacao atende ao criterio de filtro.
     */
    boolean aplicar(Avaliacao avaliacao);
}
