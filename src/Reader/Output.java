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
            // Escrever cabeçalho
            writer.append("Tipo;Valor;Linha;Coluna");
            writer.append("\n");

            // Escrever dados
            for (Token t : listaDeTokens) {
                writer.append(t.getTipo())
                        .append(",")
                        .append(String.valueOf(t.getSimbolo()))
                        .append(",")
                        .append(String.valueOf(t.getLinha()))
                        .append(",")
                        .append(String.valueOf(t.getColuna()))
                        .append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
