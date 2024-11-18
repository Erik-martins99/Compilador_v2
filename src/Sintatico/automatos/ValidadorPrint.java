package Sintatico.automatos;

import Lexico.Token;

import java.util.ArrayList;

public class ValidadorPrint {

    public ValidadorPrint() {
    }

    public boolean getValues(String value){
        String[] variablesTypes = {"ID", "TXTSTRING", "NUM_INT", "NUM_REAL"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }

    public boolean getNumeros(String value){
        String[] variablesTypes = {"ID", "NUM_INT", "NUM_REAL"};
        for(int i=0; i < variablesTypes.length; i++){
            if(value.equals(variablesTypes[i])){
                return true;
            }
        }
        return false;
    }

    public boolean getSimbolos(String value){
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

    public boolean validaPrint(ArrayList<Token> tokens) {
        int estado = 0;
        int contador = 0;
        boolean controle = true;

        if(!tokens.get(tokens.size() - 1).getTipo().equals("PV")) {
            return false;
        }
        if(tokens.get(0).getTipo().equals("FC")){
            contador++;
        }

        while(controle){
            switch(estado) {
                case 0:
                    if(tokens.get(contador).getTipo().equals("FUN_PRINT")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    if(tokens.get(contador).getTipo().equals("AP")){
                        contador++;
                    }
                    for(int i=contador; i < tokens.size(); i++) {
                        if(getValues(tokens.get(contador).getTipo())){
                            contador++;
                        } else {
                            return false;
                        }
                        if(tokens.get(contador).getTipo().equals("FP")){
                            contador++;
                            break;
                        }
                        if(tokens.get(contador - 1).getTipo().equals("TXTSTRING")){
                            if(tokens.get(contador).getTipo().equals("OPSOMA")){
                                contador++;
                            } else {
                                return false;
                            }
                        } else {
                            if(tokens.get(contador + 1).getTipo().equals("FP")){
                                return false;
                            } else {
                                if(getNumeros(tokens.get(contador + 1).getTipo())){
                                    if(getSimbolos(tokens.get(contador).getTipo())){
                                        contador++;
                                    } else {
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                    estado++;
                    break;
                case 2:
                    System.out.println(">>> Função PRINT valida!");
                    controle = false;
                    return true;
            }
        }
        return false;
    }
}
