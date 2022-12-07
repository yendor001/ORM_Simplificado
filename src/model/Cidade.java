package model;

import annotation.Id;

public class Cidade {
    private static final long serialVersionUID=1L;
    private String nome;
    @Id
    private String ibge;

    public Cidade() {
    }

    public Cidade(String nome, String ibge) {
        this.nome = nome;
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public String getIbge() {
        return ibge;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "nome='" + nome + '\'' +
                ", ibge='" + ibge + '\'' +
                '}';
    }

}
