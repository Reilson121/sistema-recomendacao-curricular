package model;

public class DisciplinaBasica extends Disciplina {

    private String area;

    public DisciplinaBasica(
            int id,
            String nome,
            int cargaHoraria,
            int periodo,
            String area) {

        super(id, nome, cargaHoraria, periodo);
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public void exibirInformacoes() {

        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Carga Horária: " + getCargaHoraria());
        System.out.println("Período: " + getPeriodo());
        System.out.println("Área: " + area);

        System.out.println("Pré-requisitos:");

        for (Disciplina d : getPreRequisitos()) {
            System.out.println("- " + d.getNome());
        }
    }
}