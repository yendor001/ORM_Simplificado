package componentes;

import exception.SerializableExcetion;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Discovery {

    private List<String> objetosSerializados =  new ArrayList<>();
    private String nomeClasse;

        public void serializarDAT(Object classe) throws SerializableExcetion {

            nomeClasse = classe.getClass().getName();

            try(FileOutputStream saida = new FileOutputStream(nomeClasse+".dat");
                ObjectOutputStream serializador =  new ObjectOutputStream(saida)){
                serializador.writeObject(classe);
                objetosSerializados.add(classe.getClass().getName()+".dat");
            }catch (IOException e){
                throw new SerializableExcetion("Ocorreu um erro ao serializar a classe " + nomeClasse +
                        " erro: " + e.getMessage());
            }
        }

        public Object desserializarDAT(String caminho) throws SerializableExcetion {
            try(FileInputStream entrada =  new FileInputStream(caminho);
                ObjectInputStream desserializador =  new ObjectInputStream(entrada)){
                Object classeDesserializada = desserializador.readObject();
                return classeDesserializada;
            }catch (IOException | ClassNotFoundException e){
                throw new SerializableExcetion("Ocorreu um erro ao desserilizar o objeto " + " erro: " + e.getMessage());
            }
        }

        public void serializarXML(Object classe) throws SerializableExcetion  {
            nomeClasse = classe.getClass().getName();
            try(
                    FileOutputStream saida = new FileOutputStream(nomeClasse + ".xml");
                    XMLEncoder serializadorXML = new XMLEncoder(saida)
            ){
                serializadorXML.writeObject(classe);
            }catch (IOException e){
                throw  new SerializableExcetion("Ocorreu um erro ao serializar a classe " + nomeClasse + " erro: "
                        + e.getMessage() );
            }
        }

        public Object desserializadorXML(String caminho) throws  SerializableExcetion{
            try(FileInputStream entrada  = new FileInputStream(caminho);
                XMLDecoder desserializadorXML = new XMLDecoder(entrada)
            ){
                Object objetoDesserializado = desserializadorXML.readObject();
                return objetoDesserializado;
            }catch (IOException e){
                throw new SerializableExcetion("Ocorreu um erro ao desserilizar o objeto " + e.getMessage());
            }
        }

        public void desserializarAll() throws SerializableExcetion{
            for(String nomeClasse : objetosSerializados){

                try(FileInputStream entrada = new FileInputStream(nomeClasse);
                    ObjectInputStream desserializador = new ObjectInputStream(entrada)){
                    Object classeDesserilizada = desserializador.readObject();
                    System.out.println(classeDesserilizada);
                }catch ( IOException | ClassNotFoundException e){

                }
            }
        }

        public void refletirAll(Object classe) throws SerializableExcetion, ClassNotFoundException {

            Class nomeclasse = Class.forName("model.Funcionario");

            //Exibir o pacote
            System.out.println(nomeclasse.getPackage()+";\n");

            //Exibir a classe
            System.out.println("public class "+nomeclasse.getName()+"{\n");

            //Exibir os atributos
            Field[] atributos = nomeclasse.getDeclaredFields();

            for(Field atributo:atributos){
                System.out.println("Private String "+atributo+";\n");
            }
            System.out.println("");

            //Exibindo os construtores
            Constructor[] construtores = nomeclasse.getConstructors();
            for (Constructor construtor:construtores){
                System.out.println("public "+construtor+"(){");
                System.out.println("}");
            }

            //Exibindo os métodos
            Method[] metodos = nomeclasse.getDeclaredMethods();
            for (Method metodo:metodos){
                System.out.println("public void "+metodo+"{");
                System.out.println("}");
            }
        }

}

