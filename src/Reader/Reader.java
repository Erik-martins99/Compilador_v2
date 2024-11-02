package Reader;

import Lexico.AnalisadorLexico;
import Lexico.Token;
import Sintatico.ValidaAtribuicao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    private String path;
    private ArrayList<Token> listaDeTokens = new ArrayList<>();
    private ValidaAtribuicao validador = new ValidaAtribuicao();

    public Reader(String path) {
        this.path = path;
    }

    public void readFile() {

        try (BufferedReader br = new BufferedReader(new FileReader(this.path))) {

            String conteudoDaLinha;
            AnalisadorLexico a = new AnalisadorLexico();
            ArrayList<Token> tokens = new ArrayList<>();
            //String stringCodigo = "";
            int linha = 1;
            boolean verificaErro = true;

            while ((conteudoDaLinha = br.readLine()) != null) {
                //stringCodigo += conteudoDaLinha + " ";
                a.setCodigo(conteudoDaLinha);
                a.setLinha(linha);
                for(int i=0; i < a.retornaTokens().size(); i++) {
                    tokens.add(a.retornaTokens().get(i));
                    if(a.retornaTokens().get(i).getTipo().equals("ERROR")) {
                        verificaErro = false;
                        break;
                    }
                }
                if(!verificaErro) {
                    break;
                }
                linha ++;
            }
            //a.setCodigo(stringCodigo);
            for (int i=0; i < tokens.size(); i++) {
                System.out.println(tokens.get(i));
            }
            this.validador.setTokens(tokens);
            this.validador.validadorSintatico();

            Output.gerarTxt(tokens);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
