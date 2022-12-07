package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class Reflection {
    private List<String> objetosRefletidos =  new ArrayList<>();
    private String nomeClasse;

    public String refletirObjetos(Object classe){
        nomeClasse = classe.getClass().getSimpleName();
        return nomeClasse;
    }

    public String getListColumun() throws ClassNotFoundException {
        Class classe = forName("model.Funcionario");

        String fieldName, result = new String();
        Field f;
        Field fields[];

        Field[] atributos = classe.getDeclaredFields();
        for (Field atributo : atributos) {
            if (!Modifier.isStatic(atributo.getModifiers())) {
                fieldName =  atributo.getName();
                result+=fieldName + ",";
            }
        }
        return result.substring(0, result.length() - 1)+")";
    }

    public String getListValues() throws ClassNotFoundException {
        Class classe = forName("model.Funcionario");

        String fieldName, result = new String();
        Field f;
        Field fields[];

        Field[] atributos = classe.getDeclaredFields();
        for (Field atributo : atributos) {
            if (!Modifier.isStatic(atributo.getModifiers())) {
                fieldName =  atributo.getName();
                result+= "?,";
            }
        }
        return result.substring(0, result.length()-1);
    }
}

