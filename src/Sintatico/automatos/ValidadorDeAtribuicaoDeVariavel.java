package Sintatico.automatos;

import Lexico.Token;

import java.util.ArrayList;

public class ValidadorDeAtribuicaoDeVariavel {

    public ValidadorDeAtribuicaoDeVariavel() {
    }


    public boolean getVariablesTypes(String value){
        String[] variablesTypes = {"INTEGER", "DOUBLE", "STRING", "BOOLEAN"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }


    public boolean getPrimeiroOuUltimoValor(String value){
        String[] variablesTypes = {"ID", "NUM_INT", "NUM_REAL", "VL_TRUE", "VL_FALSE", "AP", "FP", "TXTSTRING"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }


    public boolean getSimbolos(String value){
        String[] variablesTypes = {"OPSOMA", "OPSUB", "OPMUL", "OPDIV", "OPMAIOR", "OPMENOR",
                "OPMAIORIGUAL", "OPMENORIGUAL", "OPNEG", "OPDIFE", "OPIGUAL"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }


    public boolean getFinal(String value){
        String variableEnd = "PV";
        if(value.equals(variableEnd)){
            return true;
        }
        return false;
    }


    public boolean validaAtribuicao(ArrayList<Token> tokens) {
        int estado = 0;
        int contador = 0;
        boolean controle = true;

        if(tokens.get(0).getTipo().equals("FC")){
            contador++;
        }

        while(controle){
            switch(estado) {
                case 0:
                    if(getVariablesTypes(tokens.get(contador).getTipo())){
                        estado ++;
                        contador ++;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if(tokens.get(contador).getTipo().equals("ID")){
                        estado ++;
                        contador ++;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    if(tokens.get(contador).getTipo().equals("OPATRIBUICAO")){
                        estado ++;
                        contador ++;
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    if (tokens.get(contador).getTipo().equals("AP")) {
                        contador++;
                    }
                    if(getPrimeiroOuUltimoValor(tokens.get(contador).getTipo())){
                        contador ++;
                        if (contador >= (tokens.size())) {
                            return false;
                        }
                        else if(getFinal(tokens.get(contador).getTipo())){
                            estado ++;
                            break;
                        }
                        else {
                            while (true) {
                                if (getSimbolos(tokens.get(contador).getTipo())) {
                                    contador++;
                                } else {
                                    return false;
                                }
                                if (tokens.get(contador).getTipo().equals("AP")) {
                                    contador++;
                                }
                                if (getPrimeiroOuUltimoValor(tokens.get(contador).getTipo())) {
                                    contador++;
                                } else {
                                    System.out.println("ERROR: Valor atribuido invalido 2!!!");
                                    System.out.println(tokens.get(contador));
                                    return false;
                                }
                                if (tokens.get(contador).getTipo().equals("FP")) {
                                    contador++;
                                }
                                if (getFinal(tokens.get(contador).getTipo())) {
                                    estado++;
                                    break;
                                }
                                if (contador >= (tokens.size())) {
                                    return false;
                                }
                            }
                        }

                    } else {
                        System.out.println(tokens.get(contador));
                        return false;
                    }
                    break;
                case 4:
                    System.out.println(">>> Variavel valida!");
                    controle = false;;
                    return true;
            }
        }
        return false;
    }
}
