
public class MatrizTransiciones
{
    //columnas 
    public static final int COLUMN_LETRA = 0;
    public static final int COLUMN_DIGITO = 1;
    public static final int COLUMN_GUIONBAJO = 2;
    public static final int COLUMN_MAS = 3;
    public static final int COLUMN_COMILLA = 4;
    public static final int COLUMN_BARRA = 5; 
    public static final int COLUMN_PUNTO = 6;
    public static final int COLUMN_TAB = 7;
    public static final int COLUMN_ASIG = 8;
    public static final int COLUMN_COMA = 9;
    public static final int COLUMN_PUNTOYCOMA = 10;
    public static final int COLUMN_PARIZQ = 11;
    public static final int COLUMN_PARDER = 12;
    public static final int COLUMN_LLAVEIZQ = 13;
    public static final int COLUMN_LLAVEDER = 14;
    public static final int COLUMN_OPARMUL= 15;
    public static final int COLUMN_OPLOGNEG = 16;
    public static final int COLUMN_OPRELMAYOR = 17;
    public static final int COLUMN_ESPACIO = 18;
    public static final int COLUMN_EOL = 19;
    public static final int COLUMN_OTROCARACTER = 20;
    public static final int COLUMN_MENOS = 21;
   
    
    //matriz para saber a que estado voy según el carácter leído y el estado en el que estoy
    public int[][] estados;

    //matriz para saber que acción realizar según el carácter leído y el estado en el que estoy
    public char[][] acciones;

    public MatrizTransiciones() 
    {
        this.estados = 
        { 
            { 1,  2,  1,  3,  8,  4, -1,  0, 25, 21, 22, 19, 20, 17, 18, 16, 23, 24,  0,  0, -1,  2},
            { 1,  1,  1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11},
            {12,  2, 12, 12, 12, 12,  6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12},
            {-1, -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1,  5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            { 5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  5,  0,  5,  5},
            {-1,  7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {14,  7, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14},
            { 8,  8,  8,  8, 15,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8,  8}
        };
       
        this.acciones = 
        {

            {'B','E','B','A','L','A','-1','A','K','K','K','K','K','K','K','K','K','K','A','A','-1','Y'},
            {'C','C','C','D','D','D','D','D','D','D','D','D','D','D','D','D','D','D','D','D','D','D'},
            {'G','F','G','G','G','G','H','G','G','G','G','G','G','G','G','G','G','G','G','G','G','G'},
            {'-1','-1','-1','K','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1'},
            {'-1','-1','-1','-1','-1','A','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1', '-1'},
            {'A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A'},
            {'-1','I','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1','-1'},
            {'J','I','J','J','J','J','J','J','J','J','J','J','J','J','J','J','J','J','J','J','J','J'},
            {'M','M','M','M','N','M','M','M','M','M','M','M','M','M','M','M','M','M','M','M','M', 'M'}
       }; 
        
        
    }

    public int convierteCharAInt(char caracter)
    {
        if((caracter >= 'a' && caracter <= 'Z') || (caracter >= 'A' && caracter <= 'Z')) return COLUMN_LETRA;
        if(caracter >= '0' && caracter <= '9') return COLUMN_DIGITO;
        switch(caracter)
        {
            case '_': return COLUMN_GUIONBAJO;
            case ' '; return COLUMN_ESPACIO;
            case '/t': return COLUMN_TAB;
            case '\r': return COLUMN_EOL;
            case '.': return COLUMN_PUNTO;
            case '>': return COLUMN_OPRELMAYOR;
            case '+': return COLUMN_MAS;
            case '*': return COLUMN_OPARMUL;
            case '/': return COLUMN_BARRA;
            case '!': return COLUMN_OPLOGNEG;
            case '"': return COLUMN_COMILLA;
            case '(': return COLUMN_PARIZQ;
            case ')': return COLUMN_PARDER;
            case '{': return COLUMN_LLAVEIZQ;
            case '}': return COLUMN_LLAVEDER;
            case ',': return COLUMN_COMA;
            case ';': return COLUMN_PUNTOYCOMA;
            case '=': return COLUMN_ASIG;
            case '-': return COLUMN_MENOS;
            default: return COLUMN_OTROCARACTER;
            
        }
    }

    public int getDiguienteEstado(int estadoActual, char actual)
    {
        columna = convierteCharAInt(caracter);
        return estados[estadoActual][columna];
    }

    public int getAccion(int estadoActual, char caracter)
    {
        columna = convierteCharAInt(caracter);
        return acciones[estadoActual][columna];
    }
}
