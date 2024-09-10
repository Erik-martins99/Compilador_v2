package Lexico;

public class Token {
    private String tipo;
    private String simbolo;
    private int linha;
    private int coluna;

    public Token(String tipo, String simbolo, int linha, int coluna) {
        this.tipo = tipo;
        this.simbolo = simbolo;
        this.linha = linha;
        this.coluna = coluna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        int maxTipoLength = 15;
        int maxSimboloLength = 10;
        int maxSLineLength = 10;

        return String.format(
                "<%-" + maxTipoLength + "s : %-"+ maxSimboloLength + "s : %d          : %d",
                this.tipo + ">", this.simbolo, this.linha, this.coluna);
    }
}
