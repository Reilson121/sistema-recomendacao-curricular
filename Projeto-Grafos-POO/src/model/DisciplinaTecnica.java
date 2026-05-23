package model;

public class DisciplinaTecnica extends Disciplina {

    private String ementa;


    public DisciplinaTecnica(int id, String nome, int cargaHoraria, int periodo, String ementa) {
        super(id, nome, cargaHoraria, periodo);
        this.ementa = ementa;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    @Override
    public void exibirInformacoes() {

        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Carga Horária: " + getCargaHoraria());
        System.out.println("Período: " + getPeriodo());
        System.out.println("Ementa: " + ementa);

        System.out.println("Pré-requisitos:");

        for (Disciplina d : getPreRequisitos()) {
            System.out.println("- " + d.getNome());
        }
    }
}