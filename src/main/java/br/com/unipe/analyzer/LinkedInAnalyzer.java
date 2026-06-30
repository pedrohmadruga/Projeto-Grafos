package br.com.unipe.analyzer;

import br.com.unipe.exception.AnalysisException;
import br.com.unipe.exception.ErrorHandler;
import br.com.unipe.graph.Grafo;
import br.com.unipe.model.Vertice;

import java.util.*;
import java.util.stream.Collectors;

public class LinkedInAnalyzer {

    private final Grafo grafo;

    public LinkedInAnalyzer(Grafo grafo) {
        ErrorHandler.requireNonNull(grafo, AnalysisException.Type.NULL_GRAPH);
        this.grafo = grafo;
    }

    public Map<String, Integer> sugereConexoes(String nome) {
        Vertice usuario = ErrorHandler.requirePresent(
                grafo.encontraVertice(nome), AnalysisException.Type.PERSON_NOT_FOUND);

        List<Vertice> primeiroGrau = usuario.getAdjacencias();
        Map<String, Integer> amigosEmComum = new HashMap<>();

        for (Vertice amigo : primeiroGrau) {
            for (Vertice candidato : amigo.getAdjacencias()) {
                if (candidato.equals(usuario) || primeiroGrau.contains(candidato)) {
                    continue;
                }
                amigosEmComum.merge(candidato.getNome(), 1, Integer::sum);
            }
        }

        return amigosEmComum.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
 