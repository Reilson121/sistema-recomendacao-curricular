# Sistema de Estrutura Curricular com Grafos

Sistema em Java que modela a grade curricular do curso de Ciência da Computação (CCO) como um grafo direcionado acíclico (DAG), aplicando algoritmos de ordenação topológica para determinar sequências válidas de realização de disciplinas.

## Integrantes

- João Ricardo Santana
- Leonardo Maciel da Paz
- Otávio Fernandes Santos e Silva
- Reilson Batista da Fonseca

## Descrição do Problema

O curso de CCO possui disciplinas com pré-requisitos que formam uma hierarquia de dependências. Este sistema representa essas relações como um grafo onde cada disciplina é um vértice e cada pré-requisito é uma aresta direcionada. A partir disso, aplica os algoritmos de **DFS** e **Kahn** para gerar ordenações topológicas válidas, identificar disciplinas gargalo e calcular o custo mínimo de conclusão do curso.

O currículo modelado contém **45 disciplinas** e **22 relações de pré-requisito** distribuídas em 9 períodos.

## Funcionalidades

- Carregar disciplinas e pré-requisitos automaticamente a partir de arquivos CSV
- Exibir o grafo curricular (lista de adjacência)
- Verificar se o grafo possui ciclos
- Executar ordenação topológica via DFS
- Executar ordenação topológica via algoritmo de Kahn
- Identificar disciplinas gargalo
- Calcular tempo mínimo de conclusão do curso
- Listar disciplinas por período
- Exibir estatísticas do currículo

## Organização das Classes

```
src/
├── main/
│   └── Main.java                  # Ponto de entrada — menu interativo
├── model/
│   ├── Disciplina.java            # Superclasse abstrata
│   ├── DisciplinaBasica.java      # Subclasse — disciplinas básicas
│   ├── DisciplinaTecnica.java     # Subclasse — disciplinas técnicas
│   ├── DisciplinaOptativa.java    # Subclasse — disciplinas optativas
│   ├── TipoDisciplina.java        # Enum de tipos
│   └── TipoOferta.java            # Enum de oferta
├── grafo/
│   └── GrafoCurricular.java       # Estrutura do grafo (lista de adjacência)
├── algoritmo/
│   ├── DFS.java                   # Ordenação topológica por busca em profundidade
│   └── KahnTopologicalSort.java   # Ordenação topológica pelo algoritmo de Kahn
├── sistema/
│   └── SistemaAcademico.java      # Fachada que coordena grafo e algoritmos
└── util/
    └── LeitorCSV.java             # Leitura dos arquivos de dados

dados/
├── disciplinas.csv                # 45 disciplinas do curso de CCO
└── prerequisitos.csv              # 22 relações de pré-requisito
```

## Como Executar

### Pré-requisitos

- Java JDK 11 ou superior instalado
- Terminal (Command Prompt, PowerShell, ou terminal Linux/macOS)

### Passos

1. Clone ou baixe o repositório:
   ```bash
   git clone https://github.com/seu-usuario/sistema-recomendacao-curricular.git
   ```

2. Navegue até a pasta do projeto:
   ```bash
   cd sistema-recomendacao-curricular/Projeto-Grafos-POO
   ```

3. Compile todos os arquivos Java:
   ```bash
   javac -d out src/model/*.java src/grafo/*.java src/algoritmo/*.java src/sistema/*.java src/util/*.java src/main/*.java
   ```

4. Execute o programa a partir da pasta `Projeto-Grafos-POO`:
   ```bash
   java -cp out main.Main
   ```

> ⚠️ O programa deve ser executado a partir da pasta `Projeto-Grafos-POO/` pois os arquivos CSV são carregados pelo caminho relativo `dados/disciplinas.csv`.

### Exemplo de execução

Ao iniciar, o sistema exibirá o menu:

```
===== SISTEMA ACADÊMICO =====
1 - Exibir Grafo
2 - Exibir Disciplinas
3 - Verificar Ciclos
4 - Executar DFS
5 - Executar Kahn
...
```

## Algoritmos Implementados

| Algoritmo | Complexidade | Classe |
|-----------|-------------|--------|
| DFS (Depth-First Search) | O(V + E) | `algoritmo/DFS.java` |
| Kahn (BFS + grau de entrada) | O(V + E) | `algoritmo/KahnTopologicalSort.java` |

## Disciplinas Envolvidas

- Teoria dos Grafos
- Desenvolvimento Orientado a Objeto
- Engenharia de Software
