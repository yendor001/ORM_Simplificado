package test;

import DAO.CRUD;
import jdbc.ConnectionFactory;
import model.Funcionario;
import reflection.Reflection;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class TestaInserir {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Funcionario p1 = new Funcionario();
        Reflection r = new Reflection();
        CRUD crud = new CRUD();

        Class classe = forName("model.Funcionario");
        Field[] atributos = classe.getDeclaredFields();

        Connection connection = new ConnectionFactory().getConnection();

        //crud.Inserir();

        crud.getLista();

    }

}
