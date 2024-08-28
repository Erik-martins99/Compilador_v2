package Lexico;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalisadorLexico {
    private String codigo;
    private TabelaSimbolos tabela = new TabelaSimbolos();
    private int linha;

    public AnalisadorLexico() {};

    public  ArrayList<String> retornaExpressoes() {
        String regex = "\\d+(\\.\\d+)?|[+\\-*/()]|[a-z]+([A-Z]+)?([a-z]|[A-Z]|[0-9]|.)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(this.codigo);

        ArrayList<String> tokens = new ArrayList<>();
        while (m.find()) {
            tokens.add(m.group());
        }
        return tokens;
    }

    public ArrayList<Token> retornaTokens() {
        ArrayList<String> expressoes = retornaExpressoes();
        ArrayList<Token> tokens = new ArrayList<>();

        for (int i=0; i < expressoes.size(); i++) {
            var token = new Token(tabela.retornaTipo(
                    expressoes.get(i)), expressoes.get(i), this.linha);
            tokens.add(token);
        }
        return tokens;
    }

    public void mostraTabela() {
        ArrayList<Token> tokens = retornaTokens();

        for(int i=0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i).toString());
        }
    }

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
}
