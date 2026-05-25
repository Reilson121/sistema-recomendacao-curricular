package util;

import grafo.GrafoCurricular;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.HashMap;
import java.util.Map;

public class LeitorCSV {

    // Método responsável pelo carregamento das disciplinas
    public static Map<String, Disciplina> carregarDisciplinas(String caminho) {

        // Estrutura utilizada para armazenar as disciplinas
        Map<String, Disciplina> disciplinas = new HashMap<>();

        try {

            // Abertura do arquivo CSV
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            // Leitura do cabeçalho
            String linha = br.readLine();

            // Variável utilizada para gerar IDs automáticos
            int id = 1;

            // Percorre todas as linhas do arquivo
            while ((linha = br.readLine()) != null) {

                // Separação dos dados da linha
                String[] partes = linha.split(",");

                // Dados da disciplina
                String codigo = partes[0];
                String nome = partes[1];
                int periodo = Integer.parseInt(partes[2]);
                String tipo = partes[3];
                int cargaHoraria = Integer.parseInt(partes[4]);

                // Variável auxiliar para criação da disciplina
                Disciplina disciplina;

                // Verificação do tipo da disciplina
                if (tipo.equals("N")) {

                    disciplina = new DisciplinaBasica(
                            codigo,
                            id,
                            nome,
                            cargaHoraria,
                            periodo,
                            "GERAL"
                    );

                } else if (tipo.equals("H")) {

                    disciplina = new DisciplinaTecnica(
                            codigo,
                            id,
                            nome,
                            cargaHoraria,
                            periodo,
                            "EMENTA"
                    );

                } else {

                    disciplina = new DisciplinaOptativa(
                            codigo,
                            id,
                            nome,
                            cargaHoraria,
                            periodo,
                            "CATEGORIA"
                    );
                }

                // Adiciona a disciplina no HashMap
                disciplinas.put(codigo, disciplina);

                // Incrementa o ID
                id++;
            }

            // Fechamento do arquivo
            br.close();

        } catch (Exception e) {

            // Exibição de erro
            e.printStackTrace();
        }

        // Retorna todas as disciplinas carregadas
        return disciplinas;
    }

    // Método responsável pelo carregamento dos pré-requisitos
    public static void carregarPrerequisitos(
            String caminho,
            GrafoCurricular grafo,
            Map<String, Disciplina> disciplinas) {

        try {

            // Abertura do arquivo CSV
            BufferedReader br = new BufferedReader(new FileReader(caminho));

            // Leitura do cabeçalho
            String linha = br.readLine();

            // Percorre todas as linhas do arquivo
            while ((linha = br.readLine()) != null) {

                // Separação dos dados da linha
                String[] partes = linha.split(",");

                // Captura origem e destino
                String origem = partes[0];
                String destino = partes[1];

                // Recupera as disciplinas no HashMap
                Disciplina pre = disciplinas.get(origem);
                Disciplina disciplina = disciplinas.get(destino);

                // Adiciona a aresta no grafo
                grafo.adicionarAresta(pre, disciplina);
            }

            // Fechamento do arquivo
            br.close();

        } catch (Exception e) {

            // Exibição de erro
            e.printStackTrace();
        }
    }
}