package model;

public class DsiciplinaOptativa extends Disciplina{

    private String categoria;

    public DsiciplinaOptativa(int id, String nome, int cargaHoraria, int periodo, String categoria) {
        super(id, nome, cargaHoraria, periodo);
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

        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNome());
        System.out.println("Carga Horária: " + getCargaHoraria());
        System.out.println("Período: " + getPeriodo());
        System.out.println("Categoria: " + categoria);

        System.out.println("Pré-requisitos:");

        for (Disciplina d : getPreRequisitos()) {
            System.out.println("- " + d.getNome());
        }
    }

}
