package test;

import DAO.Dao;
import model.Funcionario;

public class TestaCriarTabela {
    public static void main(String[] args) {
        Dao<Funcionario> dao = new Dao<Funcionario>("jdbc:mysql://localhost:3306/test", "root", "");
        dao.createTable(Funcionario.class);
    }
}
