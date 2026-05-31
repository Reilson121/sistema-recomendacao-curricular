package sistema;

import grafo.GrafoCurricular;
import algoritmo.DFS;
import algoritmo.KahnTopologicalSort;

import model.Disciplina;
import model.TipoDisciplina;

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

    // Busca disciplina pelo nome
    public Disciplina buscarDisciplinaPorNome(String nome) {

        for (Disciplina d :
                grafo.obterTodasDisciplinas()) {

            if (d.getNome().equalsIgnoreCase(nome)) {

                return d;
            }
        }

        return null;
    }

    // Lista disciplinas de um período específico
    public void listarDisciplinasPorPeriodo(int periodo) {

        System.out.println(
                "\n===== DISCIPLINAS DO "
                        + periodo
                        + "º PERÍODO ====="
        );

        boolean encontrou = false;

        for (Disciplina d :
                grafo.obterTodasDisciplinas()) {

            if (d.getPeriodo() == periodo) {

                System.out.println(
                        d.getCodigo()
                                + " - "
                                + d.getNome()
                );

                encontrou = true;
            }
        }

        if (!encontrou) {

            System.out.println(
                    "Nenhuma disciplina encontrada."
            );
        }
    }

    // Exibe estatísticas do currículo
    public void exibirEstatisticas() {

        int total = 0;
        int basicas = 0;
        int tecnicas = 0;
        int optativas = 0;

        for (Disciplina d :
                grafo.obterTodasDisciplinas()) {

            total++;

            if (d.getTipo() ==
                    TipoDisciplina.BASICA) {

                basicas++;

            } else if (d.getTipo() ==
                    TipoDisciplina.TECNICA) {

                tecnicas++;

            } else if (d.getTipo() ==
                    TipoDisciplina.OPTATIVA) {

                optativas++;
            }
        }

        System.out.println(
                "\n===== ESTATÍSTICAS ====="
        );

        System.out.println(
                "Total de disciplinas: "
                        + total
        );

        System.out.println(
                "Disciplinas básicas: "
                        + basicas
        );

        System.out.println(
                "Disciplinas técnicas: "
                        + tecnicas
        );

        System.out.println(
                "Disciplinas optativas: "
                        + optativas
        );
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

        if (grafo.possuiCiclo()) {

            System.out.println(
                    "\nNão é possível calcular "
                            + "o tempo mínimo porque "
                            + "o grafo possui ciclo."
            );

            return;
        }

        grafo.calcularTempoMinimo();
    }

    // Verifica se o grafo possui ciclos
    public void verificarCiclos() {

        System.out.println(
                "\n===== VERIFICAÇÃO DE CICLOS ====="
        );

        if (grafo.possuiCiclo()) {

            System.out.println(
                    "O grafo possui ciclo."
            );

        } else {

            System.out.println(
                    "O grafo é acíclico."
            );
        }
    }


    // Exibe disciplinas que possuem pré-requisitos
    public void exibirDisciplinasComPreRequisitos() {

        System.out.println(
                "\n===== DISCIPLINAS COM PRÉ-REQUISITOS ====="
        );

        boolean encontrou = false;

        for (Disciplina disciplina :
                grafo.obterTodasDisciplinas()) {

            if (!disciplina.getPreRequisitos().isEmpty()) {

                System.out.println(
                        "\n" + disciplina.getNome()
                );

                System.out.println(
                        "Pré-requisitos:"
                );

                for (Disciplina pre :
                        disciplina.getPreRequisitos()) {

                    System.out.println(
                            "- " + pre.getNome()
                    );
                }

                encontrou = true;
            }
        }

        if (!encontrou) {

            System.out.println(
                    "Nenhuma disciplina possui pré-requisitos."
            );
        }
    }

    // Limpa sistema
    public void limparSistema() {

        grafo.limparGrafo();

        System.out.println(
                "Sistema acadêmico limpo com sucesso."
        );
    }
}