package model;

import java.util.List;
import java.util.ArrayList;

public abstract class Disciplina {

    private int id;
    private String nome;
    private int cargaHoraria;
    private int periodo;
    private List<Disciplina> preRequisitos;

    public Disciplina(int id, String nome, int cargaHoraria, int periodo) {

        this.id = id;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.periodo = periodo;
        this.preRequisitos = new ArrayList<>();
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

    public List<Disciplina> getPreRequisitos() {
        return preRequisitos;
    }

    public void setPreRequisitos(List<Disciplina> preRequisitos) {
        this.preRequisitos = preRequisitos;
    }

    public void adicionarPreRequisito(Disciplina d) {
        preRequisitos.add(d);
    }

    public abstract void exibirInformacoes();
}