package main;

import model.Disciplina;
import sistema.SistemaAcademico;
import util.LeitorCSV;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // Criação do sistema acadêmico
        // internamente já cria o grafo, DFS e Kahn
        SistemaAcademico sistema = new SistemaAcademico();

        // Carrega todas as disciplinas do arquivo CSV
        Map<String, Disciplina> disciplinas =
                LeitorCSV.carregarDisciplinas(
                        "dados/disciplinas.csv"
                );

        // Cadastra cada disciplina no sistema
        for (Disciplina d : disciplinas.values()) {

            sistema.cadastrarDisciplina(d);
        }

        // Carrega os pré-requisitos e cria as arestas do grafo
        LeitorCSV.carregarPrerequisitos(
                "dados/prerequisitos.csv",
                sistema.getGrafo(),
                disciplinas
        );

        // Exibe a estrutura do grafo curricular
        sistema.exibirGrafo();

        // Exibe informações detalhadas de todas as disciplinas
        // polimorfismo: chama exibirInformacoes() de cada subclasse
        sistema.exibirTodasDisciplinas();

        // Executa ordenação topológica usando DFS
        sistema.executarDFS();

        // Executa ordenação topológica usando algoritmo de Kahn
        sistema.executarKahn();

        // Identifica disciplinas gargalo
        sistema.exibirGargalos();

        // Calcula tempo mínimo estimado do curso
        sistema.exibirTempoMinimo();
    }
}