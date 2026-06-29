package br.com.unipe.exception;

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
}
