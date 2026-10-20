import java.io.*;
import tslib.*;

public class AnalizadorLexico {

	private char caracter;
	private int linea;
	private TS_Gestor TS;
	private MatrizTransiciones AFD;
	private BufferedReader ficheroLectura;
	private BufferedWriter ficheroEscritura;

	public AnalizadorLexico(char caracter,TS_Gestor TS, MatrizTransiciones AFD, BufferedReader ficheroLectura,
			BufferedWriter ficheroEscritura) {
		this.caracter = caracter;
		this.linea=0;
		this.TS = TS;
		this.AFD = AFD;
		this.ficheroLectura = ficheroLectura;
		this.ficheroEscritura = ficheroEscritura;
	}

	public char leer() throws IOException {

		int c = ficheroLectura.read();
		if (c == -1) {
			return '\0';
		}
		return (char) c;
	}

	public int valor() {

		return Character.getNumericValue(caracter);
		

	}

	public Token genToken(String codigo) throws IOException {
		Token token = new Token(codigo, " ");
		ficheroEscritura.write(token.toString());
		ficheroEscritura.newLine();
		return token;

	}

	public Token genToken(String codigo, String atributo) throws IOException {
		Token token = new Token(codigo, atributo);
		ficheroEscritura.write(token.toString());
		ficheroEscritura.newLine();
		return token;
	}

	public Token genToken(String codigo, int atributo) throws IOException {
		Token token = new Token(codigo, atributo);
		ficheroEscritura.write(token.toString());
		ficheroEscritura.newLine();
		return token;
	}

	public Token genToken(String codigo, double atributo) throws IOException {
		Token token = new Token(codigo, atributo);
		ficheroEscritura.write(token.toString());
		ficheroEscritura.newLine();
		return token;
	}

	public Token ALex() throws IOException, Exception {
		int estado_actual = 0;
		char accion = ' ';
		double valor = 0;
		String lexema = "";
		int contador = 0;
		Token token = null;
		boolean numNeg=false;
		while (estado_actual < 9) {
			accion = AFD.getAccion(estado_actual, caracter);
			estado_actual = AFD.getSiguienteEstado(estado_actual, caracter);
			if( caracter =='\n') linea++;
			if (estado_actual == -1) {
				throw new Exception("ERROR en la línea:"+ linea+ ", desde el estado actual no se puede cambiar a otro estado con el caracter:"+ caracter );
			} else {
				switch (accion) {
				case 'A':
					caracter = leer();
					break;
				case 'B':
					lexema += caracter;
					caracter = leer();
					break;
				case 'C':
					lexema = lexema + caracter;
					caracter = leer();
					break;
				case 'D':
					int p = TS.getEntradaTPalabrasReservadas(lexema);
					if (p != 0) {
						token = genToken(lexema);
					} else {
						p = TS.getEntradaTSGlobal(lexema);
						if (p == 0) {
							p = TS.addEntradaTSGlobal(lexema);
						}
						token = genToken("ID", p);
						
					}
					break;
				case 'E':
					valor = valor();
					caracter = leer();
					break;
				case 'F':
					valor = valor * 10 + valor();
					caracter = leer();
					break;
				case 'G':
					if (valor <= 32767) {
						token = genToken("cteEnt", numNeg? (int)-valor: (int) valor);
					} else {
						throw new Exception("ERROR en la línea:"+ linea + ", el número introducido ocupa mas de 16 bytes");
						
					}
					//numNeg=false;
					break;
				case 'H':
					contador = 0;
					caracter = leer();
					break;
				case 'I':
					valor = valor * 10 + valor();
					contador++;
					caracter = leer();
					break;
				case 'J':
				    valor = valor * Math.pow(10, -contador);
					System.out.println(valor);
					if (valor <= 1175494325.0){
						token = genToken("cteR",numNeg? -valor: valor);
					} else {
						throw new Exception("ERROR en la línea:"+ linea + ", el número introducido ocupa más de 32 bytes");
						
					}
					//numNeg=false;
					break;
				case 'K':
					caracter = leer();
					token = genToken("asig");
					break;
				case 'L':
					contador = 0;
					lexema = "\"";
					caracter = leer();
					break;
				case 'M':
					lexema = lexema + caracter;
					contador++;
					caracter = leer();
					break;
				case 'N':
					lexema = lexema + "\"";
					if (contador <= 64) {
						token = genToken("cad", lexema);
						caracter = leer();
					} else {
						throw new Exception("ERROR en la línea:"+ linea + ", la cadena introducida tiene más de 64 caracteres");
					}
					break;
				case 'O':caracter= leer();
					  token= genToken("autoInc");
					  break;
				case 'P':caracter= leer();
				  token= genToken("coma");
				  break;
				case 'Q':caracter= leer();
				  token= genToken("puntoc");
				  break;
				case 'R':caracter= leer();
				  token= genToken("parIzq");
				  break;
				case 'S':caracter= leer();
				  token= genToken("parDer");
				  break;
				case 'T':caracter= leer();
				  token= genToken("llaveIzq");
				  break;
				case 'U':caracter= leer();
				  token= genToken("llaveDer");
				  break;
				case 'V':caracter= leer();
				  token= genToken("OpArMul");
				  break;
				case 'W':caracter= leer();
				  token= genToken("OpLogNeg");
				  break;
				case 'X':caracter= leer();
				  token= genToken("OpRelMayor");
				  break;
				case 'Y': numNeg=true;
				    caracter = leer();
				  
					
				}
			}
		}
		return token;
	}
	
	
	public char getCar() {
		return caracter;
	}

	public static void main(String[] args) {
		TS_Gestor gestor = new TS_Gestor("tablaDeSimbolos.txt");
		int res = gestor.createTPalabrasReservadas();
		if (res != 0) {
			System.out.print("Error en la creación de la Tabla de Palabras Reservadas");
		}
		gestor.addEntradaTPalabrasReservadas("boolean");
		gestor.addEntradaTPalabrasReservadas("do");
		gestor.addEntradaTPalabrasReservadas("while");
		gestor.addEntradaTPalabrasReservadas("echo");
		gestor.addEntradaTPalabrasReservadas("float");
		gestor.addEntradaTPalabrasReservadas("function");
		gestor.addEntradaTPalabrasReservadas("if");
		gestor.addEntradaTPalabrasReservadas("int");
		gestor.addEntradaTPalabrasReservadas("let");
		gestor.addEntradaTPalabrasReservadas("prompt");
		gestor.addEntradaTPalabrasReservadas("return");
		gestor.addEntradaTPalabrasReservadas("string");
		gestor.addEntradaTPalabrasReservadas("void");

		gestor.createAtributo(" ", TS_Gestor.DescripcionAtributo.DIR, TS_Gestor.TipoDatoAtributo.ENTERO);
		gestor.createAtributo("parámetro", TS_Gestor.DescripcionAtributo.PARAM, TS_Gestor.TipoDatoAtributo.ENTERO);

		res= gestor.createTSGlobal();

		if (res != 0) {
			System.out.print("Error en la creación de la Tabla de Simblos");
		}
		try {
			BufferedReader ficheroLectura = new BufferedReader(new FileReader("Prueba.txt"));
			BufferedWriter ficheroEscritura = new BufferedWriter(new FileWriter("Tokens.txt"));
			int car = ficheroLectura.read();
			if(car == -1) {
				System.out.println("Fichero vacio");
				return;
			} 
			char caracter= (char) car;
			MatrizTransiciones AFD = new MatrizTransiciones();
			AnalizadorLexico analizador = new AnalizadorLexico(caracter,gestor, AFD, ficheroLectura, ficheroEscritura);
			while (analizador.getCar() != '\0') {
				analizador.ALex();
			}
			gestor.write(TS_Gestor.Tabla.GLOBAL);
			gestor.destroy(TS_Gestor.Tabla.GLOBAL);
			ficheroLectura.close();
			ficheroEscritura.close();

		} catch (IOException e) {
			System.out.println("Error al leer o escribir en el fichero: " + e.getMessage());
		
		} catch (Exception e) {
		System.out.println("Error al intentar analizar el fichero: " + e.getMessage());
		}

	}
}


