import java.io.BufferedReader;
import java.io.IOException;

public class AnalizadorLexico
{
    public char leer(BufferedReader fichero) throws IOException
    {
        int caracter = fichero.read();
        if(caracter == -1)
        {
              return '\0';
        }
            return (char) caracter;
    }
    public static void main(String[] args)
    {
        TS_Gestor = new TS_Gestor("tablaDeSimbolos.txt");
        int res = gestor.createTPalabrasReservadas();
        if(res!=0) System.out.println("Error en la creacion de la Tabla de Palabra Reservadas");
        gestor.addEntradaTPalabraReservadas("boolean");
        gestor.addEntradaTPalabraReservadas("do");
        gestor.addEntradaTPalabraReservadas("while");
        gestor.addEntradaTPalabraReservadas("echo");
        gestor.addEntradaTPalabraReservadas("float");
        gestor.addEntradaTPalabraReservadas("function");
        gestor.addEntradaTPalabraReservadas("if");
        gestor.addEntradaTPalabraReservadas("int");
        gestor.addEntradaTPalabraReservadas("let");
        gestor.addEntradaTPalabraReservadas("prompt");
        gestor.addEntradaTPalabraReservadas("return");
        gestor.addEntradaTPalabraReservadas("string");
        gestor.addEntradaTPalabraReservadas("void");
        int res = gestor.createTSGlobal();
        if(res!=0) System.out.println("Error en la creación de la Tabla de Simbolos Global");
    }
}
