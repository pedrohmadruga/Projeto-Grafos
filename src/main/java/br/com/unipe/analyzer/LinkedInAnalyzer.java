package br.com.unipe.analyzer;

import br.com.unipe.exception.AnalysisException;
import br.com.unipe.exception.ErrorHandler;
import br.com.unipe.graph.Grafo;
import br.com.unipe.graph.ResultadoCaminho;
import br.com.unipe.model.Vertice;

import java.util.*;
import java.util.stream.Collectors;

public class LinkedInAnalyzer {

    private final Grafo grafo;

    public LinkedInAnalyzer(Grafo grafo) {
        ErrorHandler.exigeNaoNulo(grafo, AnalysisException.Type.GRAFO_NULO);
        this.grafo = grafo;
    }

    public Map<String, Integer> sugereConexoes(String nome) {
        Vertice usuario = ErrorHandler.exigePresente(
                grafo.encontraVertice(nome), AnalysisException.Type.PESSOA_NAO_ENCONTRADA);

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

    public ResultadoCaminho rotaMaiorAfinidade(String origem, String destino) {
        ErrorHandler.exigePresente(
                grafo.encontraVertice(origem), AnalysisException.Type.PESSOA_NAO_ENCONTRADA);
        ErrorHandler.exigePresente(
                grafo.encontraVertice(destino), AnalysisException.Type.PESSOA_NAO_ENCONTRADA);

        return grafo.dijkstra(origem, destino);
    }

    public List<List<String>> mapearGruposIsolados() {
        ErrorHandler.exigeNaoNulo(grafo, AnalysisException.Type.GRAFO_NULO);
        
        List<Vertice> todosVertices = grafo.getVertices();
        Set<Vertice> visitados = new HashSet<>();
        List<List<String>> gruposIsolados = new ArrayList<>();
        
        for (Vertice vertice : todosVertices) {
            if (visitados.contains(vertice)) {
                continue;
            }
            
            //DFS para encontrar todos os vertices conectados
            List<String> caminhoCompleto = grafo.dfsIterativo(vertice.getNome(), null);
            
            //converter para vertices
            List<Vertice> componenteConexo = caminhoCompleto.stream()
                    .map(nome -> grafo.encontraVertice(nome)
                            .orElseThrow(() -> new AnalysisException(
                                    AnalysisException.Type.PESSOA_NAO_ENCONTRADA,
                                    "Pessoa '" + nome + "' não encontrada ao mapear grupos")))
                    .toList();
            
            //marcar como visitados
            visitados.addAll(componenteConexo);
            
            //ordenar alfabeticamente e adicionar ao resultado
            List<String> nomesDoBrupo = componenteConexo.stream()
                    .map(Vertice::getNome)
                    .sorted()
                    .toList();
            
            gruposIsolados.add(nomesDoBrupo);
        }
        
        //ordenar grupos do maior para o menor
        return gruposIsolados.stream()
                .sorted((grupo1, grupo2) -> Integer.compare(grupo2.size(), grupo1.size()))
                .toList();
    }
}

 