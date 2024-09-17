import Reader.Reader;
import Reader.Output;

public class Main {
    public static void main(String[] args) {

        Reader r = new Reader("src/codigo.txt");
        r.readFile();
    }
}