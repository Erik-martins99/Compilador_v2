package Lexico;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisadorLexico {
    private String codigo;
    private TabelaSimbolos tabela = new TabelaSimbolos();
    private int linha = 1;

    public AnalisadorLexico() {};

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public  ArrayList<String> retornaExpressoes() {
        String regex = "\".*\"|\\d+(\\.\\d+)?|([<][=])|([>][=])|([!][=])|[+\\-*/()=!<>;]|([a-z]|[A-Z])+([A-Z]+)?([a-z]|[A-Z]|[0-9])*|\\S+[^;]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(this.codigo);

        ArrayList<String> expressoes = new ArrayList<>();
        while (m.find()) {
            expressoes.add(m.group());
        }
        return expressoes;
    }

    public ArrayList<Token> retornaTokens() {
        ArrayList<String> expressoes = retornaExpressoes();
        ArrayList<Token> tokens = new ArrayList<>();
        int coluna = 1;

        for (int i=0; i < expressoes.size(); i++) {
            var token = new Token(tabela.retornaTipo(
                    expressoes.get(i)),
                    expressoes.get(i),
                    this.linha);
            tokens.add(token);

            if(tabela.retornaTipo(expressoes.get(i)) == "PV") {
                this.linha ++;
            }

            if(tabela.retornaTipo(expressoes.get(i)) == "PV") {
                coluna = 0;
            }

            token.setColuna(coluna);
            coluna ++;
        }

        return tokens;
    }

    public void mostraTabela() {
        ArrayList<Token> tokens = retornaTokens();

        for(int i=0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i).toString());
        }
    }
}
