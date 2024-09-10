package Reader;

import Lexico.AnalisadorLexico;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String path;

    public Reader(String path) {
        this.path = path;
    }

    public void readFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {

            String linha;
            AnalisadorLexico a = new AnalisadorLexico();
            int nLinha = 1;

            while ((linha = br.readLine()) != null) {
                a.setCodigo(linha);
                a.setLinha(nLinha);
                a.mostraTabela();
                nLinha++;
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
