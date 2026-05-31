package model;

import java.util.List;
import java.util.ArrayList;

public abstract class Disciplina {

    private String codigo;
    private int id;
    private String nome;
    private int cargaHoraria;
    private int periodo;
    private TipoDisciplina tipo;
    private List<Disciplina> preRequisitos;

    public Disciplina(
            String codigo,
            int id,
            String nome,
            int cargaHoraria,
            int periodo,
            TipoDisciplina tipo) {

        this.codigo = codigo;
        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.periodo = periodo;
        this.preRequisitos = new ArrayList<>();
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public TipoDisciplina getTipo() {
        return tipo;
    }

    public void setTipo(TipoDisciplina tipo) {
        this.tipo = tipo;
    }

    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public void adicionarPreRequisito(Disciplina d) {

        if (d != null && d != this && !preRequisitos.contains(d)) {
            preRequisitos.add(d);
        }
    }

    protected void exibirPreRequisitos() {

        System.out.println("Pré-requisitos:");

        if (preRequisitos.isEmpty()) {

            System.out.println("Nenhum pré-requisito.");

        } else {

            for (Disciplina d : preRequisitos) {
                System.out.println("- " + d.getNome());
            }
        }
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Disciplina outra = (Disciplina) obj;

        return codigo.equals(outra.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }

    public abstract void exibirInformacoes();
}