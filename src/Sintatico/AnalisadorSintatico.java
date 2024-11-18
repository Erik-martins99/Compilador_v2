package Sintatico;

import Lexico.Token;
import Sintatico.automatos.ValidadorBreak;
import Sintatico.automatos.ValidadorDeAtribuicaoDeVariavel;
import Sintatico.automatos.ValidadorIf;
import Sintatico.automatos.ValidadorPrint;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnalisadorSintatico {
    private ArrayList<Token> tokens;
    private ArrayList<Token> expressao = new ArrayList<>();

    private ValidadorDeAtribuicaoDeVariavel validadorDeAtribuicaoDeVariavel = new ValidadorDeAtribuicaoDeVariavel();
    private ValidadorPrint validadorPrint = new ValidadorPrint();
    private ValidadorIf validadorIf = new ValidadorIf();
    private ValidadorBreak validadorBreak = new ValidadorBreak();

    public AnalisadorSintatico() {}

    public void getExpressao(int start) {
        this.expressao.clear();

        for (; start < this.tokens.size(); start++) {
            this.expressao.add(tokens.get(start));

            if(!this.expressao.stream().map(e -> e.getTipo()).collect(Collectors.toList()).contains("AC")) {
                if (tokens.get(start).getTipo().equals("PV")) {
                    break;
                }
            } else {
                if (tokens.get(start).getTipo().equals("AC")) {
                    break;
                }
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
            else if(tiposDeTokens.get(i).equals("AC")){
                count++;
            }
        }
        if(!tiposDeTokens.get(tiposDeTokens.size()-1).equals("PV")){
            count++;
        }
        return count;
    }

    public boolean validadorSintatico(){
        int point = 0;
        getExpressao(point);
        boolean valid = false;

        for (int i=0; i<getNumExpressoes(); i++) {
            if(validaParenteres()){
                if(this.validadorDeAtribuicaoDeVariavel.validaAtribuicao(this.expressao)) {
                    point += this.expressao.size();
                    getExpressao(point);
                    valid = true;
                } else if(this.validadorPrint.validaPrint(this.expressao)){
                    point += this.expressao.size();
                    getExpressao(point);
                    valid = true;
                } else if(this.validadorIf.validaIf(this.expressao)){
                    point += this.expressao.size();
                    getExpressao(point);
                    valid = true;
                } else if(this.validadorIf.validaElse(this.expressao)) {
                    point += this.expressao.size();
                    getExpressao(point);
                    if(this.expressao.get(0).getTipo().equals("FC")){
                        this.expressao.remove(0);
                        valid = true;
                    }
                } else if(this.validadorBreak.validaBreak(this.expressao)) {
                    point += this.expressao.size();
                    getExpressao(point);
                    valid = true;
                } else if(this.tokens.get(this.tokens.size()-1).getTipo().equals("FC")){
                    this.tokens.remove(this.tokens.size()-1);
                }
                else {
                    System.out.print("ERRO:>>>");
                    for(int j=0; j < this.expressao.size(); j++){
                        System.out.print(this.expressao.get(j).getSimbolo() + " ");
                    }
                    System.out.print("<<<!!!" +
                            " LINHA: " +
                            this.expressao.get(0).getLinha() +
                            " COLUNA: " +
                            this.expressao.get(0).getColuna());
                    return false;
                }
            } else {
                break;
            }
            for(int j=0; j <this.expressao.size(); j++){
                //System.out.println(this.expressao.get(j));
            }
        }
        return valid;
    }

    public void valida(){
        if(validadorSintatico()){
            System.out.println("EXPRESSÂO ACEITA!!!");
        } else {
            System.out.println("EXPRESSÂO NÃO ACEITA!!!");
        }
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }
}
