package br.com.unipe.analyzer;

import br.com.unipe.app.LinkedInApp;
import br.com.unipe.exception.AnalysisException;
import br.com.unipe.graph.Grafo;
import br.com.unipe.graph.ResultadoCaminho;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LinkedInAnalyzerTest {

    private LinkedInAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        Grafo grafo = LinkedInApp.criarRedeDeTeste();
        analyzer = new LinkedInAnalyzer(grafo);
    }

    @Test
    void sugereConexoesOrdenaPorAmigosEmComum() {
        Map<String, Integer> sugestoes = analyzer.sugereConexoes("Ana");

        assertEquals(2, sugestoes.size());
        assertEquals(List.of("Eduardo", "Fernanda"), List.copyOf(sugestoes.keySet()));
        assertEquals(2, sugestoes.get("Eduardo"));
        assertEquals(1, sugestoes.get("Fernanda"));
    }

    @Test
    void grauSeparacaoContatoDireto() {
        assertEquals(1, analyzer.grauSeparacao("Ana", "Bruno"));
    }

    @Test
    void grauSeparacaoContatoIndireto() {
        assertEquals(2, analyzer.grauSeparacao("Ana", "Fernanda"));
    }

    @Test
    void grauSeparacaoMesmaPessoa() {
        assertEquals(0, analyzer.grauSeparacao("Ana", "Ana"));
    }

    @Test
    void grauSeparacaoInalcançavel() {
        assertEquals(-1, analyzer.grauSeparacao("Ana", "Gabriel"));
    }

    @Test
    void rotaMaiorAfinidadeEncontraMenorCustoPonderado() {
        ResultadoCaminho resultado = analyzer.rotaMaiorAfinidade("Ana", "Fernanda");

        assertEquals(3, resultado.custo());
        assertEquals(List.of("Ana", "Bruno", "Eduardo", "Fernanda"), resultado.caminho());
    }

    @Test
    void mapearGruposIsoladosIdentificaTresComponentes() {
        List<List<String>> grupos = analyzer.mapearGruposIsolados();

        assertEquals(3, grupos.size());
        assertEquals(List.of("Ana", "Bruno", "Carlos", "Daniela", "Eduardo", "Fernanda"), grupos.get(0));
        assertEquals(List.of("Gabriel", "Hugo"), grupos.get(1));
        assertEquals(List.of("Igor", "Juliana"), grupos.get(2));
    }

    @Test
    void pessoaInexistenteLancaExcecao() {
        AnalysisException ex = assertThrows(AnalysisException.class,
                () -> analyzer.sugereConexoes("Inexistente"));

        assertEquals(AnalysisException.Type.PESSOA_NAO_ENCONTRADA, ex.getType());
    }
}
