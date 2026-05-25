package main;

import algoritmo.DFS;
import algoritmo.KahnTopologicalSort;
import grafo.GrafoCurricular;
import model.Disciplina;
import util.LeitorCSV;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Criação do grafo curricular
        GrafoCurricular grafo = new GrafoCurricular();

        // Carrega todas as disciplinas do arquivo CSV
        Map<String, Disciplina> disciplinas =
                LeitorCSV.carregarDisciplinas(
                        "dados/disciplinas.csv"
                );

        // Adiciona todas as disciplinas no grafo
        for (Disciplina d : disciplinas.values()) {

            grafo.adicionarDisciplina(d);
        }

        // Carrega os pré-requisitos e cria as arestas do grafo
        LeitorCSV.carregarPrerequisitos(
                "dados/prerequisitos.csv",
                grafo,
                disciplinas
        );

        // Exibe mensagem inicial
        System.out.println(
                "===== GRAFO CURRICULAR ====="
        );

        // Mostra o grafo no console
        grafo.mostrarGrafo();

        // Executa ordenação topológica usando DFS
        DFS dfs = new DFS(grafo);

        dfs.ordenacaoTopologica();

        // Executa ordenação topológica usando algoritmo de Kahn
        KahnTopologicalSort kahn =
                new KahnTopologicalSort(grafo);

        kahn.executarOrdenacao();

        kahn.mostrarOrdenacao();

        // Identifica disciplinas gargalo
        grafo.identificarGargalos();

        // Calcula tempo mínimo estimado do curso
        grafo.calcularTempoMinimo();
    }
}