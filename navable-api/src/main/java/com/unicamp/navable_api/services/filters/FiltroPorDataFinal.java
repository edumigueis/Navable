package com.unicamp.navable_api.services.filters;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import java.time.LocalDate;


public class FiltroPorDataFinal implements FiltroAvaliacaoStrategy {
    private final LocalDate dataFinal;

    public FiltroPorDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public boolean aplicar(Avaliacao avaliacao) {
        return dataFinal == null || !avaliacao.getTimestamp().isAfter(dataFinal);
    }
}