package Sintatico;

import Lexico.Token;
import Sintatico.automatos.ValidadorDeAtribuicaoDeVariavel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisadorSintatico {
    private ArrayList<Token> tokens;
    private ArrayList<Token> expressao = new ArrayList<>();

    private ValidadorDeAtribuicaoDeVariavel validadorDeAtribuicaoDeVariavel = new ValidadorDeAtribuicaoDeVariavel();

    public AnalisadorSintatico() {}

    public void getExpressao(int start) {
        this.expressao.clear();
        for (; start < this.tokens.size(); start++) {
            this.expressao.add(tokens.get(start));
            if (tokens.get(start).getTipo().equals("PV")) {
                break;
            }
        }
    }

    public boolean validaParenteres(){
        List<String> tiposDeTokens = this.expressao.stream().map(t -> t.getTipo()).collect(Collectors.toList());
        int countAP = 0;
        int countFP = 0;
        for (int i=0; i < tiposDeTokens.size(); i++) {
            if(tiposDeTokens.get(i).equals("AP")){
                countAP++;
            } else if(tiposDeTokens.get(i).equals("FP")) {
                countFP++;
            }
        }
        if(countFP == countAP){
            return true;
        }
        if(countFP < countAP){
            System.out.println("ERRO: Simbolo \"(\" sem correspondencia!");
        }
        else{
            System.out.println("ERRO: Simbolo \")\" sem correspondencia!");
        }
        return false;
    }

    public int getNumExpressoes(){
        int count = 0;
        List<String> tiposDeTokens = this.tokens.stream().map(t -> t.getTipo()).collect(Collectors.toList());
        for (int i=0; i < tiposDeTokens.size(); i++) {
            if(tiposDeTokens.get(i).equals("PV")){
                count++;
            }
        }
        return count;
    }

    public void validadorSintatico(){
        getExpressao(0);
        for (int i=0; i<getNumExpressoes(); i++) {
            if(validaParenteres()){
                System.out.println(this.validadorDeAtribuicaoDeVariavel.validaAtribuicao(this.expressao));
                System.out.println(getNumExpressoes());
                System.out.println(this.tokens.size());
                System.out.println(this.expressao.size());
                getExpressao(this.expressao.size());
            } else {
                break;
            }
        }
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
}
