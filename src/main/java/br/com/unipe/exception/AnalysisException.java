package br.com.unipe.exception;

public class AnalysisException extends RuntimeException {

    public enum Type {
        NULL_GRAPH("Graph cannot be null."),
        PERSON_NOT_FOUND("Person not found in the network."),
        INVALID_ARGUMENT("Invalid argument.");

        private final String defaultMessage;

        Type(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }
    }

    private final Type type;

    public AnalysisException(Type type) {
        super(type.getDefaultMessage());
        this.type = type;
    }

    public AnalysisException(Type type, String message) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
