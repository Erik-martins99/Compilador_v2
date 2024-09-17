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

            String linha;
            AnalisadorLexico a = new AnalisadorLexico();
            int nLinha = 1;
            String teste = "";

            while ((linha = br.readLine()) != null) {
                //a.setCodigo(linha);
                //a.setLinha(nLinha);
                //a.mostraTabela();

                //System.out.println(linha);
                teste += linha + " ";
                /*
                for(int i=0; i < a.retornaTokens().size(); i++) {
                    this.listaDeTokens.add(a.retornaTokens().get(i));
                }

                for(int i=0; i < this.listaDeTokens.size(); i++) {
                    System.out.println(this.listaDeTokens.get(i));
                }*/
                //nLinha++;
            }
            a.setCodigo(teste);
            a.mostraTabela();
            //System.out.println(teste);
            Output.gerarTxt(a.retornaTokens());
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
