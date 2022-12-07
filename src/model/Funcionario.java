package model;

import annotation.Id;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class Funcionario  implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    private  String cpf;
    private String nome;
    private int salario;

    public Funcionario() {
    }

    public Funcionario(String cpf, String nome, int salario) {
        this.cpf = cpf;
        this.nome = nome;
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        //long oldSalario = this.salario;
        this.salario = salario;
        //changeSupport.firePropertyChange("salario",oldSalario,this.salario);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", salario=" + salario +
                '}';
    }

    /*
    PropertyChangeSupport changeSupport=new PropertyChangeSupport(this);

    public void addPropertyChangeListener(PropertyChangeListener ouvinte){
        changeSupport.addPropertyChangeListener(ouvinte);
    }

    public void removePropertyChangeListener(PropertyChangeListener ouvinte){
        changeSupport.removePropertyChangeListener(ouvinte);
    }
    */
}
