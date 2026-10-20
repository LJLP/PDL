
public class Token {

	private String codigo;
	private Object atributo;
	
	public Token(String codigo, Object atributo) {
		this.codigo=codigo;
		this.atributo= atributo;
	}
	
	
	public String getcodigo() {
		return codigo;
	}
	
	public Object atributo() {
		return atributo;
	}
	
	public String toString() {
		return "< " + codigo + ", " + atributo +" >";
	}
	
}
