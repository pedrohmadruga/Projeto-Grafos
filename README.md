# Projeto LinkedIn Analyzer

Motor de análises e recomendações para uma rede social de conexões profissionais, modelada como **grafo não-direcionado e ponderado**.

## Requisitos

- Java 21
- Gradle (wrapper incluído)

## Como executar

```bash
# Windows
gradlew.bat run

# Linux/macOS
./gradlew run
```

A aplicação principal (`LinkedInApp`) monta o cenário de teste do enunciado e demonstra:

- Sugestão de conexões (amigos de 2º grau)
- Grau de separação entre duas pessoas
- Rota de maior afinidade (menor custo ponderado via Dijkstra)
- Mapeamento de grupos isolados (componentes conexos)

## Como rodar os testes

```bash
gradlew.bat test
```

## Estrutura do projeto

```
src/main/java/br/com/unipe/
├── app/LinkedInApp.java       # Cenário de demonstração
├── analyzer/LinkedInAnalyzer.java
├── graph/Grafo.java           # DFS, BFS, Dijkstra
├── model/Vertice.java, Aresta.java
└── exception/                 # Tratamento de erros
```

## Entrega do projeto

Para a avaliação, o grupo deve enviar:

1. **Código-fonte** neste repositório (público no GitHub)
2. **Link de vídeo explicativo** no YouTube (público ou não listado)
3. **Nome completo e RGM** de todos os integrantes (máximo 5 pessoas)
