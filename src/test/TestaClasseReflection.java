package test;

import componentes.Discovery;
import exception.SerializableExcetion;
import model.Cidade;
import model.Funcionario;
import ouvinte.ChangeOuvinte;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static java.lang.Class.forName;

public class TestaClasseReflection {

    public static void main(String[] args){

        Funcionario funcionario = new Funcionario();
        Cidade cidade = new Cidade("Brasília","125874");
        Discovery serializar = new Discovery();
        ChangeOuvinte ouvinte = new ChangeOuvinte();

        //funcionario.addPropertyChangeListener(ouvinte);

        funcionario.setNome("Rodney");
        funcionario.setSalario(4200);

        try{
            serializar.serializarXML(cidade);
            serializar.serializarXML(funcionario);
            serializar.refletirAll(cidade);
            serializar.refletirAll(funcionario);

            Funcionario funcionarioDesserializadoXML = (Funcionario) serializar.desserializadorXML("model.Funcionario.xml");
            Cidade cidadeDesserializadaXML = (Cidade) serializar.desserializadorXML("model.Cidade.xml");

            System.out.println("Funcionario: " +  funcionarioDesserializadoXML.toString());
            System.out.println("Cidade: " + cidadeDesserializadaXML.toString());

        }catch (SerializableExcetion | ClassNotFoundException e){
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public static class TestaCidadeReflection {
        public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

            System.out.println("\nClasse\n");

            Class classe = forName("model.Cidade");

            System.out.println("Classe " + classe.getName());
            System.out.println("Pacote " + classe.getPackage());
            System.out.println("Modificador de Acesso "+ classe.getModifiers());

            System.out.println("\nAtributos\n");
            Field[] atributos = classe.getDeclaredFields();

            for(Field atributo: atributos){
                if(!Modifier.isStatic(atributo.getModifiers())){
                    System.out.println(atributo.getName());
                }
            }

            System.out.println("\nMetodos\n");
            Method[] metodos = classe.getDeclaredMethods();

            for(Method metodo:metodos){
                System.out.println(metodo);
            }

            System.out.println("\nCriando o objeto Cidade\n");

            Object objeto = classe.getDeclaredConstructor().newInstance();

            Class [] parametrosString = {String.class};
            Method metodo1 = classe.getMethod("setNome",parametrosString);

            Object[] valoresString = {"Manaus"};

            metodo1.invoke(objeto,valoresString);

            Class [] parametrosVoid={};
            Method metodo2 = classe.getMethod("getNome",parametrosVoid);

            Object[] valoresVoid = {};
            Object retorno = metodo2.invoke(objeto,valoresVoid);

            System.out.println(retorno);

            Cidade cidade = new Cidade();
            cidade.setNome("Manaus");
        }
    }
}
