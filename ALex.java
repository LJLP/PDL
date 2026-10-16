import java.io.BufferedReader;
import java.io.IOException;

public class ALex
{
    String[] palabrasReservadas = {"boolean", "do", "while", "echo", "float", "function", "if", "int", "let", "prompt", "return", "string", "void", "while"};
    public char leer(BufferedReader fichero) throws IOException
    {
        int caracter = fichero.read();
        if(caracter == -1)
        {
              return '\0';
        }
            return (char) caracter;
    }

    public boolean esPalRes(String palabra)
    {
        boolean encontrado = false;
        for(int i = 0; i<palabrasReservadas.length && !encontrado; i++)
            {
                if(palabrasReservadas[i].equals(palabra)) encontrado=true;
            }
        return encontrado;
    }
}
