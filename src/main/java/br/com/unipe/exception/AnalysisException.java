package br.com.unipe.exception;

public class AnalysisException extends RuntimeException {

    public enum Type {
        GRAFO_NULO("O grafo não pode ser nulo."),
        PESSOA_NAO_ENCONTRADA("Pessoa não encontrada na rede."),
        ARGUMENTO_INVALIDO("Argumento inválido.");

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
