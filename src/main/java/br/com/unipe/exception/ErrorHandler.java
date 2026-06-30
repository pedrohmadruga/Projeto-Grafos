package br.com.unipe.exception;

import java.util.Optional;

public final class ErrorHandler {

    private ErrorHandler() {
    }

    public static void exigeNaoNulo(Object valor, AnalysisException.Type tipo) {
        if (naoEstaVazio(valor)) {
            return;
        }
        throw new AnalysisException(tipo);
    }

    private static boolean naoEstaVazio(Object valor) {
        return valor != null;
    }

    public static <T> T exigePresente(Optional<T> optional, AnalysisException.Type tipo) {
        return optional.orElseThrow(() -> new AnalysisException(tipo));
    }
}
