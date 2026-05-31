package main;

import model.Disciplina;
import sistema.SistemaAcademico;
import util.LeitorCSV;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Criação do sistema acadêmico
        SistemaAcademico sistema = new SistemaAcademico();

        // Carrega disciplinas
        Map<String, Disciplina> disciplinas =
                LeitorCSV.carregarDisciplinas(
                        "dados/disciplinas.csv"
                );

        // Cadastra disciplinas
        for (Disciplina d : disciplinas.values()) {

            sistema.cadastrarDisciplina(d);
        }

        // Carrega pré-requisitos
        LeitorCSV.carregarPrerequisitos(
                "dados/prerequisitos.csv",
                sistema.getGrafo(),
                disciplinas
        );

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println(
                    "\n===== SISTEMA ACADÊMICO ====="
            );

            System.out.println(
                    "1 - Exibir Grafo"
            );

            System.out.println(
                    "2 - Exibir Disciplinas"
            );

            System.out.println(
                    "3 - Verificar Ciclos"
            );

            System.out.println(
                    "4 - Executar DFS"
            );

            System.out.println(
                    "5 - Executar Kahn"
            );

            System.out.println(
                    "6 - Exibir Gargalos"
            );

            System.out.println(
                    "7 - Exibir Estatísticas"
            );

            System.out.println(
                    "8 - Exibir Tempo Mínimo"
            );

            System.out.println(
                    "9 - Listar Disciplinas por Período"
            );

            System.out.println(
                    "10 - Exibir Disciplinas com Pré-requisitos"
            );

            System.out.println(
                    "0 - Sair"
            );

            System.out.print(
                    "\nEscolha uma opção: "
            );

            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    // Exibe a estrutura do grafo curricular
                    sistema.exibirGrafo();

                    break;

                case 2:

                    // Exibe informações detalhadas de todas as disciplinas
                    // polimorfismo: chama exibirInformacoes() de cada subclasse
                    sistema.exibirTodasDisciplinas();

                    break;

                case 3:

                    // Verifica se o grafo possui ciclos
                    sistema.verificarCiclos();

                    break;

                case 4:

                    // Executa ordenação topológica usando DFS
                    sistema.executarDFS();

                    break;

                case 5:

                    // Executa ordenação topológica usando algoritmo de Kahn
                    sistema.executarKahn();

                    break;

                case 6:

                    // Identifica disciplinas gargalo
                    sistema.exibirGargalos();

                    break;

                case 7:

                    // Exibe estatísticas do currículo
                    sistema.exibirEstatisticas();

                    break;

                case 8:

                    // Calcula tempo mínimo estimado do curso
                    sistema.exibirTempoMinimo();

                    break;

                case 9:

                    System.out.print(
                            "Informe o período: "
                    );

                    int periodo =
                            scanner.nextInt();

                    // Lista disciplinas do período informado
                    sistema.listarDisciplinasPorPeriodo(
                            periodo
                    );

                    break;

                case 10:

                    // Exibe disciplinas que possuem pré-requisitos
                    sistema.exibirDisciplinasComPreRequisitos();

                    break;

                case 0:

                    System.out.println(
                            "\nEncerrando sistema..."
                    );

                    break;

                default:

                    System.out.println(
                            "\nOpção inválida."
                    );
            }

        } while (opcao != 0);

        scanner.close();
    }
}