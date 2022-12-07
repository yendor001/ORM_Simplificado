package DAO;

import annotation.Id;
import jdbc.ConnectionFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao<T> {
    private String dbUrl;
    private String user;
    private String password;

    public Dao(String dbUrl, String user, String password) {
        this.dbUrl = dbUrl;
        this.user = user;
        this.password = password;
    }

    /* Métodos de Conexão // iremos utilizar o design pattern Factory
    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, user, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }*/

    /** CREATE TABLE **/
    public void createTable(Class clazz){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE ");
        stringBuilder.append(clazz.getSimpleName().toLowerCase());
        stringBuilder.append(" (");
        for(Field field : clazz.getDeclaredFields()){
            if(!Modifier.isStatic(field.getModifiers())){
                //ID primary key
                Annotation annId = field.getAnnotation(Id.class);

                stringBuilder.append(field.getName());
                stringBuilder.append(" ");
                if(field.getType().getSimpleName().equals("int")){
                    stringBuilder.append("int(11)");
                }
                if(field.getType().getSimpleName().equals("Long")){
                    stringBuilder.append("Long(12)");
                }
                if(field.getType().getSimpleName().equals("double")){
                    stringBuilder.append("double(0.0)");
                }
                if(field.getType().getSimpleName().equals("boolean")){
                    stringBuilder.append("false");
                }
                if(field.getType().getSimpleName().equals("Character")){
                    stringBuilder.append("Char(1)");
                }
                if(field.getType().getSimpleName().equals("String")){
                    stringBuilder.append("varchar(255)");
                }
                if(annId != null){
                    stringBuilder.append(" PRIMARY KEY");
                }
                stringBuilder.append(",");
            }
        }
        System.out.println(stringBuilder.substring(0, stringBuilder.toString().length()-1)+ ")");
        createTableJbdc(stringBuilder.substring(0, stringBuilder.toString().length()-1)+ ")");
    }

    private void createTableJbdc(String query){
        Connection connection = null;
        Statement statement = null;
        try {
            //implementa design pattern Factory
            connection = new ConnectionFactory().getConnection();
            statement = connection.createStatement();
            statement.execute(query);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
