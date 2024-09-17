package Reader;

import Lexico.AnalisadorLexico;
import Lexico.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    private String path;
    private ArrayList<Token> listaDeTokens = new ArrayList<>();

    public Reader(String path) {
        this.path = path;
    }

    public void readFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {

            String conteudoDaLinha;
            AnalisadorLexico a = new AnalisadorLexico();
            String stringCodigo = "";

            while ((conteudoDaLinha = br.readLine()) != null) {
                stringCodigo += conteudoDaLinha + " ";
            }
            a.setCodigo(stringCodigo);
            Output.gerarTxt(a.retornaTokens());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
