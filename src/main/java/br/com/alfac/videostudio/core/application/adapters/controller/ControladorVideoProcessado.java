package br.com.alfac.videostudio.core.application.adapters.controller;

import br.com.alfac.videostudio.core.application.usecases.AtualizarStatusVideoUseCase;
import br.com.alfac.videostudio.core.application.usecases.ErroProcessamentoVideoUseCase;

import java.util.UUID;

public class ControladorVideoProcessado {

    private final AtualizarStatusVideoUseCase atualizarStatusVideoUseCase;
    private final ErroProcessamentoVideoUseCase erroProcessamentoVideoUseCase;

    public ControladorVideoProcessado(final AtualizarStatusVideoUseCase atualizarStatusVideoUseCase, final ErroProcessamentoVideoUseCase erroProcessamentoVideoUseCase) {
        this.atualizarStatusVideoUseCase = atualizarStatusVideoUseCase;
        this.erroProcessamentoVideoUseCase = erroProcessamentoVideoUseCase;
    }

    public void processar(UUID videoName, String status) {

        switch (status) {
            case "PROCESSADO", "PROCESSANDO":
                atualizarStatusVideoUseCase.execute(videoName, status);
                break;
            case "ERRO":
                erroProcessamentoVideoUseCase.execute(videoName);
                break;
        }

    }
}
