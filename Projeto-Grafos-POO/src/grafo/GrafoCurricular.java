package grafo;

import model.Disciplina;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collection;

public class GrafoCurricular {

    // Armazena disciplinas pelo ID
    private Map<Integer, Disciplina> disciplinas;

    // Lista de adjacência do grafo
    // chave = disciplina
    // valor = lista de disciplinas dependentes
    private Map<Disciplina, List<Disciplina>> adjacencias;

    // Armazena o grau de entrada de cada disciplina
    // (quantos pré-requisitos ela possui)
    private Map<Disciplina, Integer> grauEntrada;

    // Construtor
    public GrafoCurricular() {

        disciplinas = new HashMap<>();
        adjacencias = new HashMap<>();
        grauEntrada = new HashMap<>();
    }

    // Adiciona uma disciplina ao grafo
    public void adicionarDisciplina(Disciplina d) {

        // Salva disciplina pelo ID
        disciplinas.put(d.getId(), d);

        // Cria lista vazia de adjacentes se ainda não existir
        adjacencias.putIfAbsent(d, new ArrayList<>());

        // Inicializa grau de entrada com 0
        grauEntrada.putIfAbsent(d, 0);
    }

    // Adiciona uma aresta entre disciplinas
    // origem -> destino
    // significa:
    // origem é pré-requisito de destino
    public void adicionarAresta(Disciplina origem, Disciplina destino) {

        // Garante que ambas existem no mapa
        adjacencias.putIfAbsent(origem, new ArrayList<>());
        adjacencias.putIfAbsent(destino, new ArrayList<>());

        // Inicializa grau de entrada se necessário
        grauEntrada.putIfAbsent(origem, 0);
        grauEntrada.putIfAbsent(destino, 0);

        // Adiciona destino na lista de adjacência da origem
        adjacencias.get(origem).add(destino);

        // Incrementa grau de entrada do destino
        grauEntrada.put(destino, grauEntrada.get(destino) + 1);

        // Adiciona origem como pré-requisito da disciplina destino
        destino.adicionarPreRequisito(origem);
    }

    // Retorna disciplinas adjacentes
    public List<Disciplina> obterAdjacentes(Disciplina d) {

        return adjacencias.get(d);
    }

    // Retorna todas as disciplinas cadastradas
    public Collection<Disciplina> obterTodasDisciplinas() {

        return disciplinas.values();
    }

    // Recalcula os graus de entrada
    public void calcularGrauEntrada() {

        // Zera todos os graus
        grauEntrada.clear();

        for (Disciplina d : disciplinas.values()) {
            grauEntrada.put(d, 0);
        }

        // Percorre todas as arestas
        for (Disciplina origem : adjacencias.keySet()) {

            for (Disciplina destino : adjacencias.get(origem)) {

                // Incrementa grau do destino
                grauEntrada.put(
                        destino,
                        grauEntrada.get(destino) + 1
                );
            }
        }
    }

    // Retorna grau de entrada de uma disciplina
    public int getGrauEntrada(Disciplina d) {

        return grauEntrada.get(d);
    }

    // Exibe o grafo na tela
    public void mostrarGrafo() {

        for (Disciplina d : adjacencias.keySet()) {

            System.out.print(d.getNome() + " -> ");

            // Mostra adjacentes
            for (Disciplina adj : adjacencias.get(d)) {
                System.out.print(adj.getNome() + " ");
            }

            System.out.println();
        }
    }

    // Limpa completamente o grafo
    public void limparGrafo() {

        disciplinas.clear();
        adjacencias.clear();
        grauEntrada.clear();
    }
}