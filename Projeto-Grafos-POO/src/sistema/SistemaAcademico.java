package sistema;

import grafo.GrafoCurricular;
import algoritmo.DFS;
import algoritmo.KahnTopologicalSort;

import model.Disciplina;

public class SistemaAcademico {

    // Grafo principal do sistema
    private GrafoCurricular grafo;

    // Algoritmo DFS
    private DFS dfs;

    // Algoritmo de Kahn
    private KahnTopologicalSort kahn;

    // Construtor
    public SistemaAcademico() {

        grafo = new GrafoCurricular();

        // Inicializa algoritmos
        dfs = new DFS(grafo);
        kahn = new KahnTopologicalSort(grafo);
    }

    // Retorna o grafo
    public GrafoCurricular getGrafo() {

        return grafo;
    }

    // Cadastra disciplina no sistema
    public void cadastrarDisciplina(Disciplina d) {

        grafo.adicionarDisciplina(d);

        System.out.println(
                "Disciplina cadastrada com sucesso: "
                        + d.getNome()
        );
    }

    // Adiciona pré-requisito
    // origem -> destino
    public void adicionarPreRequisito(
            Disciplina origem,
            Disciplina destino) {

        grafo.adicionarAresta(origem, destino);

        System.out.println(
                "Pré-requisito adicionado: "
                        + origem.getNome()
                        + " -> "
                        + destino.getNome()
        );
    }

    // Executa ordenação topológica usando DFS
    public void executarDFS() {

        System.out.println(
                "\n===== EXECUTANDO DFS ====="
        );

        dfs.ordenacaoTopologica();
    }

    // Executa ordenação topológica usando algoritmo de Kahn
    public void executarKahn() {

        System.out.println(
                "\n===== EXECUTANDO KAHN ====="
        );

        kahn.executarOrdenacao();

        // Verifica ciclo
        if (kahn.detectarCiclo()) {

            System.out.println(
                    "O grafo possui ciclo. "
                            + "Ordenação topológica inválida."
            );

        } else {

            kahn.mostrarOrdenacao();
        }
    }

    // Exibe todas as disciplinas cadastradas
    public void exibirTodasDisciplinas() {

        System.out.println(
                "\n===== DISCIPLINAS ====="
        );

        for (Disciplina d :
                grafo.obterTodasDisciplinas()) {

            d.exibirInformacoes();

            System.out.println(
                    "----------------------"
            );
        }
    }

    // Exibe estrutura do grafo
    public void exibirGrafo() {

        System.out.println(
                "\n===== GRAFO CURRICULAR ====="
        );

        grafo.mostrarGrafo();
    }

    // Exibe disciplinas gargalo
    public void exibirGargalos() {

        grafo.identificarGargalos();
    }

    // Exibe cálculo do tempo mínimo
    public void exibirTempoMinimo() {

        grafo.calcularTempoMinimo();
    }

    // Limpa sistema
    public void limparSistema() {

        grafo.limparGrafo();

        System.out.println(
                "Sistema acadêmico limpo com sucesso."
        );
    }
}