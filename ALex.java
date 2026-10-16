import java.io.BufferedReader;
import java.io.IOException;

public class ALex
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
}
