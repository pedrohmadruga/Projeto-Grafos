package br.com.unipe.app;

import br.com.unipe.analyzer.LinkedInAnalyzer;
import br.com.unipe.graph.Grafo;
import br.com.unipe.graph.ResultadoCaminho;

import java.util.List;
import java.util.Map;

public class LinkedInApp {

  public static void main(String[] args) {
    Grafo grafo = criarRedeDeTeste();
    LinkedInAnalyzer analyzer = new LinkedInAnalyzer(grafo);

    System.out.println("=== Sugestão de Conexões para Ana ===");
    Map<String, Integer> sugestoes = analyzer.sugereConexoes("Ana");
    sugestoes.forEach((pessoa, amigosEmComum) ->
        System.out.printf("  %s (%d amigo(s) em comum)%n", pessoa, amigosEmComum));

    System.out.println("\n=== Grau de Separação: Ana -> Fernanda ===");
    int grau = analyzer.grauSeparacao("Ana", "Fernanda");
    System.out.printf("  Passos: %d%n", grau);

    System.out.println("\n=== Rota de Maior Afinidade: Ana -> Fernanda ===");
    ResultadoCaminho rota = analyzer.rotaMaiorAfinidade("Ana", "Fernanda");
    System.out.printf("  Caminho: %s%n", String.join(" -> ", rota.caminho()));
    System.out.printf("  Custo total: %d%n", rota.custo());

    System.out.println("\n=== Grupos Isolados (Componentes Conexos) ===");
    List<List<String>> grupos = analyzer.mapearGruposIsolados();
    for (int i = 0; i < grupos.size(); i++) {
      System.out.printf("  Grupo %d: %s%n", i + 1, grupos.get(i));
    }
  }

  public static Grafo criarRedeDeTeste() {
    Grafo grafo = new Grafo(false, true);

    grafo.adicionaVertices(
        "Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda",
        "Gabriel", "Hugo", "Igor", "Juliana");

    grafo.addAresta("Ana", "Bruno", 1);
    grafo.addAresta("Ana", "Carlos", 2);
    grafo.addAresta("Ana", "Daniela", 8);
    grafo.addAresta("Bruno", "Eduardo", 1);
    grafo.addAresta("Carlos", "Eduardo", 1);
    grafo.addAresta("Daniela", "Fernanda", 5);
    grafo.addAresta("Eduardo", "Fernanda", 1);
    grafo.addAresta("Gabriel", "Hugo", 1);
    grafo.addAresta("Igor", "Juliana", 1);

    return grafo;
  }
}
