package Sintatico;

import Lexico.Token;
import Sintatico.automatos.ValidadorDeAtribuicaoDeVariavel;

import java.util.ArrayList;

public class ValidaAtribuicao {
    private ArrayList<Token> tokens;
    private ArrayList<Token> expressao = new ArrayList<>();

    private ValidadorDeAtribuicaoDeVariavel validadorDeAtribuicaoDeVariavel = new ValidadorDeAtribuicaoDeVariavel();

    public ValidaAtribuicao() {}

    public void getExpressao(int start) {
        this.expressao.clear();
        for (; start < this.tokens.size(); start++) {
            this.expressao.add(tokens.get(start));
            if (tokens.get(start).getTipo().equals("PV")) {
                //System.out.println(this.expressao);
                break;
            }
        }
    }

    public void validadorSintatico(){
        getExpressao(0);
        System.out.println(this.validadorDeAtribuicaoDeVariavel.validaAtribuicao(this.expressao));
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
}
