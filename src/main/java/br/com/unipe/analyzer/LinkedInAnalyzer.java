package br.com.unipe.analyzer;

import br.com.unipe.exception.AnalysisException;
import br.com.unipe.exception.ErrorHandler;
import br.com.unipe.graph.Grafo;

public class LinkedInAnalyzer {

    private final Grafo grafo;

    public LinkedInAnalyzer(Grafo grafo) {
        ErrorHandler.requireNonNull(grafo, AnalysisException.Type.NULL_GRAPH);
        this.grafo = grafo;
    }
}
 