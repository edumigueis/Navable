package com.unicamp.navable_api.services.filters;
import com.unicamp.navable_api.persistance.entities.Avaliacao;
import java.time.LocalDate;

public class FiltroPorDataInicial implements FiltroAvaliacaoStrategy {
    private final LocalDate dataInicial;

    public FiltroPorDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    @Override
    public boolean aplicar(Avaliacao avaliacao) {
        return dataInicial == null || !avaliacao.getTimestamp().isBefore(dataInicial);
    }
}