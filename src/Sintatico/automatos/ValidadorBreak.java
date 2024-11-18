package Sintatico.automatos;

import Lexico.Token;

import java.util.ArrayList;

public class ValidadorBreak {

    public ValidadorBreak() {
    }

    public boolean validaBreak(ArrayList<Token> tokens) {
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
                    if(tokens.get(contador).getTipo().equals("BREAK")){
                        contador ++;
                        estado ++;
                    } else {
                        return false;
                    }
                    break;
                case 1:
                    System.out.println(">>> Função BREAK valida!");
                    controle = false;
                    return true;
            }
        }
        return false;
    }
}
