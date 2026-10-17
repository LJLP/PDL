import java.io.BufferedReader;
import java.io.IOException;

public class AnalizadorLexico
{   private char caracter;
        private TS_Gestor TS;
        private MatrizTransiciones AFD;
        private BufferedReader ficheroLectura;
        private BufferedWriter ficheroEscritura;


        public AnalizadorLexico(char caracter, TS_Gestor TS, MatrizTransiciones AFD,BufferedReader ficheroLectura, BufferedWriter ficheroEscritura) {
            this.caracter = caracter;
            this.TS =TS;
            this.AFD= AFD;
            this.ficheroLectura= ficheroLectura;
            this.ficheroEscritura=ficheroEscritura;
        }

    //lectura del caracter en el caso de que se pueda leer
    public char leer() throws IOException
    {
        int caracter = ficheroLectura.read();
        if(caracter == -1)
        {
              return '\0';
        }
            return (char) caracter;
    }

    //devuelve valor del caracter
    public int valor()
    {
        return Character.getNumericValue(caracter);
    }

    public Token genToken(String codigo)
    {
        Token token = new Token(codigo, null);
        return token;
    }
    public Token genToken(String codigo, String atributo){
        Token token =new Token (codigo, atributo );
        return token;
    }
    public Token genToken(String codigo, int atributo){
        Token token= new Token (codigo, atributo );
        return token;
    }
    public Token ALex (){
        int estado_actual=0;
        char accion= null;
        int valor=null;
        String lexema=null;
        int contador=null;
        while(estado<9){
            accion=AFD.getAccion(estado_actual, caracter);
            estado=AFD.getSiguienteEstado(estado_actual, caracter);
            if(estado==-1){
                System.out.println("ERROR");
            }
            else{
                switch (accion){
                    case A: caracter=leer();
                    case B: String lexema= caracter;
                            caracter=leer();
                    case C: lexema= lexema+ caracter;
                            caracter= leer();
                    case D: int p= TS.getEntradaDePalabrasReservadas(lexema);
                            if(p != 0){
                                genToken(lexema);
                            }else{
                                p =TS.getEntradaTSGlobal(lexema);
                                if(p==0){
                                    p=TS.addEntradaTSGlobal(lexema);
                                }
                                genToken("id", p);
                            }
                    case E: valor= valor();
                            caracter=leer();
                    case F: valor= valor*10 + valor();
                    case G: if(valor <= 32767){
                                getToken("cteEnt", valor);
                                }else{
                                        System.out.println("ERROR");
                                    }
                    case H: contador=0;
                            caracter=leer();
                    case I: valor=valor*10 valor();
                            contador++;
                            caracter=leer();
                    case J: double valord=valor*Math.pow(10, -contador);
                            if(valord <= 1175494325.0){
                                genToken("cteR", valord );
                            }else{
                                System.out.println("Error");
                            }
                    case K: caracter=leer();
                            genToken(lexema);
                    case L: contador=0;
                            lexema="\"";
                            caracter=leer;
                    case M: lexema=lexema + caracter;
                            contador++;
                            caracter=leer();
                    case N: lexema = lexema + "\"";
                            if(contador<=64) genToken("cad", lexema);
                            else System.out.println("Error");
                            caracter = leer();
                }
            }
        } 
    }
    public static void main(String[] args)
    {
        TS_Gestor = new TS_Gestor("tablaDeSimbolos.txt");
        //Tabla de palabras reservadas
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

        //Tabla de simbolos global
        int res = gestor.createTSGlobal();
        if(res!=0) System.out.println("Error en la creacion de la Tabla de Simbolos Global");
    }
}








