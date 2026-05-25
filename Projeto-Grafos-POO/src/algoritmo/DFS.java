package algoritmo;

import grafo.GrafoCurricular;
import model.Disciplina;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/*
 * Classe responsável pela ordenação topológica
 * utilizando o algoritmo de Busca em Profundidade
 */
public class DFS {

    // Referência para o grafo curricular
    private GrafoCurricular grafo;

    // Estrutura utilizada para armazenar disciplinas visitadas
    private Set<Disciplina> visitados;

    // Pilha utilizada na ordenação topológica
    private Stack<Disciplina> pilha;

    // Construtor da classe
    public DFS(GrafoCurricular grafo) {

        this.grafo = grafo;
        this.visitados = new HashSet<>();
        this.pilha = new Stack<>();
    }

    // Método principal da ordenação topológica
    public void ordenacaoTopologica() {

        // Percorre todas as disciplinas do grafo
        for (Disciplina disciplina :
                grafo.obterTodasDisciplinas()) {

            // Executa DFS apenas nas disciplinas não visitadas
            if (!visitados.contains(disciplina)) {

                dfs(disciplina);
            }
        }

        // Exibe o resultado da ordenação
        System.out.println("\n===== ORDENAÇÃO TOPOLOGICA DFS =====");

        int ordem = 1;

        while (!pilha.isEmpty()) {

            Disciplina d = pilha.pop();

            System.out.println(
                    ordem + " - "
                            + d.getCodigo()
                            + " | "
                            + d.getNome()
            );

            ordem++;
        }
    }

    // Método recursivo da busca em profundidade
    private void dfs(Disciplina disciplina) {

        // Marca a disciplina como visitada
        visitados.add(disciplina);

        // Percorre os vizinhos da disciplina
        for (Disciplina vizinho :
                grafo.obterAdjacentes(disciplina)) {

            // Continua DFS apenas nos não visitados
            if (!visitados.contains(vizinho)) {

                dfs(vizinho);
            }
        }

        // Adiciona a disciplina na pilha
        pilha.push(disciplina);
    }
}