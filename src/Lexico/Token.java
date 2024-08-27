package Lexico;

public class Token {
    private String tipo;
    private String simbolo;
    private int linha;

    public Token(String tipo, String simbolo, int linha) {
        this.tipo = tipo;
        this.simbolo = simbolo;
        this.linha = linha;
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

    @Override
    public String toString() {
        // Determina o comprimento máximo dos campos 'tipo' e 'simbolo'
        int maxTipoLength = 15;  // Exemplo de valor, ajuste conforme necessário
        int maxSimboloLength = 10;  // Exemplo de valor, ajuste conforme necessário

        // Formata a saída com os campos alinhados
        return String.format("<%-" + maxTipoLength + "s : %-"+ maxSimboloLength + "s : %d",
                this.tipo + ">", this.simbolo, this.linha);
    }
}
