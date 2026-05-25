package model;

public class DisciplinaOptativa extends Disciplina {

    private String categoria;

    public DisciplinaOptativa(
            String codigo,
            int id,
            String nome,
            int cargaHoraria,
            int periodo,
            String categoria) {

        super(
                codigo,
                id,
                nome,
                cargaHoraria,
                periodo,
                TipoDisciplina.OPTATIVA
        );

        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public void exibirInformacoes() {

        System.out.println("Código: " + getCodigo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Carga Horária: " + getCargaHoraria());
        System.out.println("Período: " + getPeriodo());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Categoria: " + categoria);

        System.out.println("Pré-requisitos:");

        if (getPreRequisitos().isEmpty()) {

            System.out.println("Nenhum pré-requisito.");

        } else {

            for (Disciplina d : getPreRequisitos()) {
                System.out.println("- " + d.getNome());
            }
        }
    }
}