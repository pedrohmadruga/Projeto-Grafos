package br.com.unipe.exception;

import java.util.Optional;

public final class ErrorHandler {

    private ErrorHandler() {
    }

    public static void requireNonNull(Object value, AnalysisException.Type type) {
        if (isNotEmpty(value)) {
            return;
        }
        throw new AnalysisException(type);
    }

    private static boolean isNotEmpty(Object value) {
        return value != null;
    }

    public static <T> T requirePresent(Optional<T> optional, AnalysisException.Type type) {
        return optional.orElseThrow(() -> new AnalysisException(type));
    }
}
