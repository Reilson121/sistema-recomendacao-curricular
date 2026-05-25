package algoritmo;

import grafo.GrafoCurricular;
import model.Disciplina;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class KahnTopologicalSort {

    // Referência para o grafo curricular
    private GrafoCurricular grafo;

    // Fila utilizada no algoritmo de Kahn
    private Queue<Disciplina> fila;

    // Armazena grau de entrada das disciplinas
    private Map<Disciplina, Integer> grauEntrada;

    // Armazena ordem topológica final
    private List<Disciplina> ordemTopologica;

    // Construtor
    public KahnTopologicalSort(GrafoCurricular grafo) {

        this.grafo = grafo;
        this.fila = new LinkedList<>();
        this.grauEntrada = new HashMap<>();
        this.ordemTopologica = new ArrayList<>();
    }

    // Inicializa os graus de entrada
    public void inicializarGrauEntrada() {

        // Recalcula graus do grafo
        grafo.calcularGrauEntrada();

        // Copia os graus para o mapa local
        for (Disciplina d : grafo.obterTodasDisciplinas()) {

            grauEntrada.put(
                    d,
                    grafo.getGrauEntrada(d)
            );
        }
    }

    // Executa algoritmo de Kahn
    public void executarOrdenacao() {

        // Limpa estruturas anteriores
        fila.clear();
        ordemTopologica.clear();
        grauEntrada.clear();

        // Inicializa graus
        inicializarGrauEntrada();

        // Adiciona disciplinas sem pré-requisitos
        for (Disciplina d : grafo.obterTodasDisciplinas()) {

            if (grauEntrada.get(d) == 0) {

                fila.add(d);
            }
        }

        // Executa enquanto houver elementos na fila
        while (!fila.isEmpty()) {

            // Remove disciplina da fila
            Disciplina atual = fila.poll();

            // Adiciona na ordem topológica
            ordemTopologica.add(atual);

            // Percorre adjacentes
            for (Disciplina adj :
                    grafo.obterAdjacentes(atual)) {

                // Decrementa grau de entrada
                grauEntrada.put(
                        adj,
                        grauEntrada.get(adj) - 1
                );

                // Se grau ficou 0 entra na fila
                if (grauEntrada.get(adj) == 0) {

                    fila.add(adj);
                }
            }
        }
    }

    // Detecta se existe ciclo no grafo
    public boolean detectarCiclo() {

        return ordemTopologica.size()
                < grafo.obterTodasDisciplinas().size();
    }

    // Retorna ordem topológica
    public List<Disciplina> getOrdemTopologica() {

        return ordemTopologica;
    }

    // Exibe ordem topológica
    public void mostrarOrdenacao() {

        System.out.println("\n===== ORDEM TOPOLOGICA =====");

        // Verifica se existe ciclo
        if (detectarCiclo()) {

            System.out.println(
                    "O grafo possui ciclo."
            );

            return;
        }

        // Verifica se existe ordenação
        if (ordemTopologica.isEmpty()) {

            System.out.println(
                    "Nenhuma ordenação executada."
            );

            return;
        }

        int contador = 1;

        // Exibe ordenação topológica
        for (Disciplina d : ordemTopologica) {

            System.out.println(
                    contador + " - "
                            + d.getCodigo()
                            + " | "
                            + d.getNome()
            );

            contador++;
        }
    }
}