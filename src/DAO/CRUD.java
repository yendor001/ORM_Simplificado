package DAO;

import jdbc.ConnectionFactory;
import model.Funcionario;
import model.Pessoa;
import reflection.Reflection;
import java.lang.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private String nomeClasse;

    Funcionario p1 = new Funcionario();
    Reflection r = new Reflection();

    //Método inserir
    public void Inserir() throws SQLException, ClassNotFoundException {
        Connection connection = new ConnectionFactory().getConnection();
        String sql = "insert into " + p1.getClass().getSimpleName() +
              " (" + r.getListColumun() +
              " values (" + r.getListValues() + ")";

        System.out.println(sql);

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "666666666");
        statement.setString(2, "Pedro");
        statement.setString(3, "4000");
        statement.execute();
        statement.close();

        System.out.println("Gravado com sucesso!");
        connection.close();

        try {
           // connection = new ConnectionFactory().getConnection();
        } finally {
            connection.close();
        }
    }

    //Método deletar
    public void Delete() throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        String sql = "DELETE FROM " + p1.getClass().getSimpleName() + " WHERE " + "cpf=99999999999";
        System.out.println(sql);
        PreparedStatement statement = connection.prepareStatement(sql);
        //statement.setString(1,"11122233311");

        statement.executeUpdate(sql);
        statement.close();

        System.out.println("Dado apagado!");
        connection.close();

        try {
            connection = new ConnectionFactory().getConnection();
        } finally {
            connection.close();
        }
    }

    //método listar
   public List<Pessoa> getLista() throws SQLException {
       List<Pessoa> pessoas = new ArrayList<Pessoa>();
       Connection connection = new ConnectionFactory().getConnection();

       String sql = "SELECT * FROM " + p1.getClass().getSimpleName();
       System.out.println(sql);
       PreparedStatement statement = connection.prepareStatement(sql);

       ResultSet rs = statement.executeQuery();
       while (rs.next()) {
       // criando o objeto
       Pessoa pessoa = new Pessoa();
       pessoa.setCpf(rs.getString("cpf"));
       pessoa.setNome(rs.getString("nome"));
       pessoa.setTelefone(rs.getString("salario"));

       // adicionando o objeto à lista
       pessoas.add(pessoa);
       }
       rs.close();
       statement.close();
       return pessoas;
       }
}