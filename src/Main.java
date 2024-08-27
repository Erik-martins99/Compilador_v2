import Lexico.TabelaSimbolos;
import Lexico.Tipos;
import Reader.Reader;

public class Main {
    public static void main(String[] args) {

        Reader r = new Reader("src/codigo.txt");
        r.readFile();

    }
}