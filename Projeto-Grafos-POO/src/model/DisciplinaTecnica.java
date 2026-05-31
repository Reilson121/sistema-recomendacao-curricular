package model;

public class DisciplinaTecnica extends Disciplina {

    private String ementa;


    public DisciplinaTecnica(
            String codigo,
            int id,
            String nome,
            int cargaHoraria,
            int periodo,
            String ementa) {

        super(
                codigo,
                id,
                nome,
                cargaHoraria,
                periodo,
                TipoDisciplina.TECNICA
        );

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

        System.out.println("Código: " + getCodigo());
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Carga Horária: " + getCargaHoraria());
        System.out.println("Período: " + getPeriodo());
        System.out.println("Tipo: " + getTipo());
        System.out.println("Ementa: " + ementa);

        exibirPreRequisitos();
    }

}