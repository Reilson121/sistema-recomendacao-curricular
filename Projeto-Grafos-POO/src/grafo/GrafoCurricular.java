package grafo;

import model.Disciplina;

import java.util.*;

/*
 * Classe responsável pela estrutura do grafo curricular
 */
public class GrafoCurricular {

    // Estrutura de lista de adjacência
    private Map<Disciplina, List<Disciplina>> listaAdjacencia;

    // Estrutura utilizada para armazenar grau de entrada
    private Map<Disciplina, Integer> grauEntrada;

    // Construtor da classe
    public GrafoCurricular() {

        listaAdjacencia = new HashMap<>();
        grauEntrada = new HashMap<>();
    }

    // Método responsável por adicionar uma disciplina no grafo
    public void adicionarDisciplina(Disciplina disciplina) {

        // Adiciona a disciplina caso ela ainda não exista
        listaAdjacencia.putIfAbsent(disciplina, new ArrayList<>());

        // Inicializa grau de entrada com 0
        grauEntrada.putIfAbsent(disciplina, 0);
    }

    // Método responsável por adicionar uma aresta no grafo
    public void adicionarAresta(
            Disciplina origem,
            Disciplina destino) {

        // Adiciona o destino na lista de adjacência da origem
        listaAdjacencia.get(origem).add(destino);

        // Adiciona o pré-requisito na disciplina
        destino.adicionarPreRequisito(origem);
    }

    // Método responsável por exibir o grafo
    public void mostrarGrafo() {

        // Percorre todas as disciplinas
        for (Disciplina disciplina : listaAdjacencia.keySet()) {

            System.out.print(
                    disciplina.getNome() + " -> "
            );

            // Percorre os vizinhos da disciplina
            for (Disciplina vizinho :
                    listaAdjacencia.get(disciplina)) {

                System.out.print(
                        vizinho.getNome() + " | "
                );
            }

            System.out.println();
        }
    }

    // Método responsável pelo cálculo do grau de entrada
    public void calcularGrauEntrada() {

        // Reinicializa os graus
        for (Disciplina disciplina : listaAdjacencia.keySet()) {

            grauEntrada.put(disciplina, 0);
        }

        // Percorre todas as arestas do grafo
        for (Disciplina disciplina : listaAdjacencia.keySet()) {

            for (Disciplina adj :
                    listaAdjacencia.get(disciplina)) {

                grauEntrada.put(
                        adj,
                        grauEntrada.get(adj) + 1
                );
            }
        }
    }

    // Método responsável por retornar o grau de entrada
    public int getGrauEntrada(Disciplina disciplina) {

        return grauEntrada.get(disciplina);
    }

    // Método responsável por retornar adjacentes
    public List<Disciplina> obterAdjacentes(
            Disciplina disciplina) {

        return listaAdjacencia.get(disciplina);
    }

    // Método responsável por retornar todas as disciplinas
    public Set<Disciplina> obterTodasDisciplinas() {

        return listaAdjacencia.keySet();
    }

    // Método responsável por identificar disciplinas gargalo
    public void identificarGargalos() {

        System.out.println("\n===== DISCIPLINAS GARGALO =====");

        int maiorQuantidade = 0;

        // Verifica qual disciplina possui mais dependências
        for (Disciplina disciplina : listaAdjacencia.keySet()) {

            int quantidade =
                    listaAdjacencia.get(disciplina).size();

            if (quantidade > maiorQuantidade) {

                maiorQuantidade = quantidade;
            }
        }

        // Exibe as disciplinas gargalo
        for (Disciplina disciplina : listaAdjacencia.keySet()) {

            int quantidade =
                    listaAdjacencia.get(disciplina).size();

            if (quantidade == maiorQuantidade) {

                System.out.println(
                        disciplina.getNome()
                                + " -> "
                                + quantidade
                                + " dependências"
                );
            }
        }
    }

    // Método responsável por calcular o tempo mínimo do curso
    public void calcularTempoMinimo() {

        int maiorCaminho = obterMaiorCaminho();

        System.out.println(
                "\nMaior cadeia de pré-requisitos: "
                        + maiorCaminho
                        + " disciplinas."
        );

        System.out.println(
                "Tempo mínimo estimado considerando "
                        + "as dependências: "
                        + maiorCaminho
                        + " semestres."
        );
    }

    // Método responsável por verificar se o grafo possui ciclo
    public boolean possuiCiclo() {

        Set<Disciplina> visitados = new HashSet<>();

        Set<Disciplina> pilhaRecursao = new HashSet<>();

        for (Disciplina disciplina : listaAdjacencia.keySet()) {

            if (dfsCiclo(
                    disciplina,
                    visitados,
                    pilhaRecursao)) {

                return true;
            }
        }

        return false;
    }

    // Método auxiliar da verificação de ciclos
    private boolean dfsCiclo(
            Disciplina disciplina,
            Set<Disciplina> visitados,
            Set<Disciplina> pilhaRecursao) {

        if (pilhaRecursao.contains(disciplina)) {

            return true;
        }

        if (visitados.contains(disciplina)) {

            return false;
        }

        visitados.add(disciplina);

        pilhaRecursao.add(disciplina);

        for (Disciplina adjacente :
                listaAdjacencia.get(disciplina)) {

            if (dfsCiclo(
                    adjacente,
                    visitados,
                    pilhaRecursao)) {

                return true;
            }
        }

        pilhaRecursao.remove(disciplina);

        return false;
    }

    // Método responsável por encontrar o maior caminho do grafo
    public int obterMaiorCaminho() {

        int maior = 0;

        for (Disciplina disciplina :
                listaAdjacencia.keySet()) {

            maior = Math.max(
                    maior,
                    calcularProfundidade(disciplina)
            );
        }

        return maior;
    }

    // Método auxiliar utilizado para calcular profundidade
    private int calcularProfundidade(
            Disciplina disciplina) {

        List<Disciplina> adjacentes =
                listaAdjacencia.get(disciplina);

        if (adjacentes.isEmpty()) {

            return 1;
        }

        int maior = 0;

        for (Disciplina adjacente : adjacentes) {

            maior = Math.max(
                    maior,
                    calcularProfundidade(adjacente)
            );
        }

        return maior + 1;
    }

    // Getter da lista de adjacência
    public Map<Disciplina, List<Disciplina>>
    getListaAdjacencia() {

        return listaAdjacencia;
    }

    // Setter da lista de adjacência
    public void setListaAdjacencia(
            Map<Disciplina, List<Disciplina>>
                    listaAdjacencia) {

        this.listaAdjacencia = listaAdjacencia;
    }

    // Método responsável por limpar o grafo
    public void limparGrafo() {

        // Remove todas as disciplinas e arestas
        listaAdjacencia.clear();

        // Remove todos os graus de entrada
        grauEntrada.clear();
    }
}