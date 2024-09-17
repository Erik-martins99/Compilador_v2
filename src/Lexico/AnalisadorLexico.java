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

    public  ArrayList<String> retornaExpressoes() {
        String regex = "\"([^\"]+)\"|\\d+(\\.\\d+)?|([<][=])|([>][=])|([!][=])|[+\\-*/()=!<>;]|([a-z]|[A-Z])+([A-Z]+)?([a-z]|[A-Z]|[0-9])*|\\S+[^;]";
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

            if(retornaErro(token)) {
                tokens.remove(tokens.size()-1);
                tokens.add(token);
                break;
            }

            if(tabela.retornaTipo(expressoes.get(i)) == "PV") {
                this.linha ++;
            }

            token.setColuna(coluna);

            if(tabela.retornaTipo(expressoes.get(i)) == "PV") {
                coluna = 0;
            }

            coluna ++;
        }
        mostraTabela(tokens);

        return tokens;
    }

    private void mostraTabela( ArrayList<Token> tokens) {

        for(int i=0; i < tokens.size(); i++) {
            System.out.println(tokens.get(i).toString());
        }
    }

    public boolean retornaErro(Token t) {
        if(t.getTipo().equals("ERROR")) {
            t.setSimbolo("ERROR: value >>> " + t.getSimbolo() + " <<< Line: " + t.getLinha() + " Colunm: " + t.getColuna());
            System.out.println(t.getSimbolo());
            return true;
        }
        else {
            return false;
        }
    }
}
