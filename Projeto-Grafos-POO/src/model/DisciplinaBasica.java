package model;

public class DisciplinaBasica extends Disciplina {

    private String area;

    public DisciplinaBasica(
            String codigo,
            int id,
            String nome,
            int cargaHoraria,
            int periodo,
            String area) {

        super(codigo, id, nome, cargaHoraria, periodo,TipoDisciplina.BASICA);
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

        System.out.println("Código: " + getCodigo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Carga Horária: " + getCargaHoraria());
        System.out.println("Período: " + getPeriodo());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Área: " + area);

        exibirPreRequisitos();
    }
}