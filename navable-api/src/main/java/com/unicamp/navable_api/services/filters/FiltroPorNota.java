package com.unicamp.navable_api.services.filters;
import com.unicamp.navable_api.persistance.entities.Avaliacao;

public class FiltroPorNota implements FiltroAvaliacaoStrategy{
    private final Integer nota;

    public FiltroPorNota(Integer nota) {
        this.nota = nota;
    }

    @Override
    public boolean aplicar(Avaliacao avaliacao) {
        return nota != null && avaliacao.getNota() >= nota;
    }
}
