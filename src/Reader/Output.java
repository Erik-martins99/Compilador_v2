package Reader;

import Lexico.Token;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Output {
    public static void gerarTxt(ArrayList<Token> tokens) {

        ArrayList<Token> listaDeTokens = tokens;

        String fileName = "output_lexico.csv";

        try (FileWriter writer = new FileWriter(fileName)) {

            writer.append("Tipo;Valor;Linha;Coluna");
            writer.append("\n");

            for (Token t : listaDeTokens) {
                String simbolo = t.getSimbolo();
                if(t.getTipo().equals("PV")) {
                    simbolo = "\";\"";
                }
                String linha = String.valueOf(t.getLinha());

                if(!t.getTipo().equals("ERROR")) {
                    writer.append(t.getTipo())
                            .append(";")
                            .append(simbolo)
                            .append(";")
                            .append(linha)
                            .append(";")
                            .append(String.valueOf(t.getColuna()))
                            .append("\n");
                } else {
                    writer.append(t.getTipo())
                            .append(";")
                            .append(simbolo)
                            .append("\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
