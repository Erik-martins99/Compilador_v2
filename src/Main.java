import Reader.Reader;

public class Main {
    public static void main(String[] args) {

        if(args.length > 0) {
            Reader r = new Reader(args[0]);
            r.readFile();
        } else {
            Reader r = new Reader("src/codigo.txt");
            r.readFile();
        }
    }
}