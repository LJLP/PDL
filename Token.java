public class Token
{
    private String codigo;
    private Object atributo;

    public Token(String codigo, Object atributo)
    {
      this.codigo = codigo;
      this.atributo=atributo;
    }

    public String getCodigo()
    {
      return codigo;
    }

    public Object getAtributo()
    {
      return atributo;
    }

    @Override
    public String toString()
    {
      return "< " + codigo + " , " + atributo + " >\n";
    }
}
