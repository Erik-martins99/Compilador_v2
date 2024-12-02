package Sintatico.automatos;

import Lexico.Token;

import java.util.ArrayList;

public class ValidadorCondicional {

    public ValidadorCondicional() {
    }

    public boolean getValues(String value){
        String[] variablesTypes = {"ID", "NUM_INT", "NUM_REAL", "VL_TRUE", "VL_FALSE"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }

    public boolean getComparadores(String value){
        String[] variablesTypes = {"COND_AND", "COND_OR"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }

    public boolean getOperadores(String value){
        String[] variablesTypes = {"OPSOMA", "OPSUB", "OPMUL", "OPDIV", "OPMAIOR",
                "OPMENOR", "OPMAIORIGUAL", "OPMENORIGUAL", "OPNEG",
                "OPDIFE", "OPIGUAL"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }

    public boolean validaIf(ArrayList<Token> tokens) {
        int estado = 0;
        int contador = 0;
        boolean controle = true;

        if(!tokens.get(tokens.size() - 1).getTipo().equals("AC")) {
            return false;
        }

        if(tokens.get(0).getTipo().equals("FC")){
            contador++;
        }

        while(controle){
            switch(estado) {
                case 0:
                    if(tokens.get(contador).getTipo().equals("FUN_IF")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if(tokens.get(contador).getTipo().equals("AP")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    for(int i=contador; i < tokens.size(); i++) {
                        if (getValues(tokens.get(contador).getTipo())) {
                            contador++;
                        } else {
                            return false;
                        }
                        if (tokens.get(contador).getTipo().equals("FP")) {
                            contador++;
                            estado++;
                            break;
                        }
                        if (getOperadores(tokens.get(contador).getTipo())){
                            contador++;
                        } else {
                            return false;
                        }
                        if (getValues(tokens.get(contador).getTipo())) {
                            contador++;
                        } else {
                            return false;
                        }
                        if (tokens.get(contador).getTipo().equals("FP")) {
                            contador++;
                            estado++;
                            break;
                        }
                        if (getComparadores(tokens.get(contador).getTipo())) {
                            contador++;
                        } else {
                            return false;
                        }
                    }
                    break;
                case 3:
                    if(tokens.get(contador).getTipo().equals("AC")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 4:
                    System.out.println(">>> Função IF valida!");
                    controle = false;
                    return true;
            }
        }
        return false;
    }

    public boolean validaElse(ArrayList<Token> tokens) {
        int estado = 0;
        int contador = 0;
        boolean controle = true;

        if(!tokens.get(tokens.size() - 1).getTipo().equals("AC")) {
            return false;
        }
        if(tokens.get(0).getTipo().equals("FC")){
            //contador++;
        }

        while(controle){
            switch(estado) {
                case 0:
                    if(tokens.get(contador).getTipo().equals("FC")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if(tokens.get(contador).getTipo().equals("FUN_ELSE")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 2:
                    if(tokens.get(contador).getTipo().equals("AC")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 3:
                    System.out.println(">>> Função ELSE valida!");
                    controle = false;
                    return true;
            }
        }
        return false;
    }
}
