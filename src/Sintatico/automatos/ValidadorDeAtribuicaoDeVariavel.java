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
        String[] variablesTypes = {"ID", "NUM_INT", "NUM_REAL"};
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

        while(controle){
            switch(estado) {
                case 0:
                    if(getVariablesTypes(tokens.get(contador).getTipo())){
                        estado ++;
                        contador ++;
                    } else {
                        System.out.println("ERROR: Tipo de variavel invalida!!!");
                        return false;
                    }
                    break;
                case 1:
                    if(tokens.get(contador).getTipo().equals("ID")){
                        estado ++;
                        contador ++;
                    } else {
                        System.out.println("ERROR: Nome da variavel invalid0!!!");
                        return false;
                    }
                    break;
                case 2:
                    if(tokens.get(contador).getTipo().equals("OPATRIBUICAO")){
                        estado ++;
                        contador ++;
                    } else {
                        System.out.println("SERROR: Simbolo de atribuição não presentes!!!");
                        return false;
                    }
                    break;
                case 3:
                    if(getPrimeiroOuUltimoValor(tokens.get(contador).getTipo())){
                        contador ++;
                        if (contador >= (tokens.size())) {
                            System.out.println("ERROR: Falta do simbolo (;)!!!");
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
                                    System.out.println("ERROR: Valor atribuido invalido!!!");
                                    return false;
                                }
                                if (tokens.get(contador).getTipo().equals("AP")) {
                                    contador++;
                                }
                                if (getPrimeiroOuUltimoValor(tokens.get(contador).getTipo())) {
                                    contador++;
                                } else {
                                    System.out.println("ERROR: Valor atribuido invalido!!!");
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
                                    System.out.println("ERROR: Falta do simbolo (;)!!!");
                                    return false;
                                }
                            }
                        }

                    } else {
                        System.out.println("ERROR: Valor atribuido invalido!!!");
                        return false;
                    }
                    break;
                case 4:
                    System.out.println("Variavel valida!");
                    controle = false;;
                    return true;
            }
        }
        return false;
    }
}
