package Lexico;

public class TabelaSimbolos {
    private Tipos t;
    private String[][] tabelaDeSimbolos = {{
            t.OPSOMA.toString(),
            t.OPSUB.toString(),
            t.OPMUL.toString(),
            t.OPDIV.toString(),
            t.AP.toString(),
            t.FP.toString(),
            t.OPATRUI.toString(),}, {"+", "-", "*", "/", "(", ")", "="}};


    public String[][] getTabelaDeSimbolos() {
        return tabelaDeSimbolos;
    }

    public void setTabelaDeSimbolos(String[][] simbolicTable) {
        this.tabelaDeSimbolos = simbolicTable;
    }

    public String retornaTipo(String value) {
        String tipo;

        if (value.matches("[0-9][0-9]*")) {
            return t.NUM_INT.toString();
        } else if(value.matches("([0-9][0-9]*)\\.([0-9]|[0-9][0-9])")) {
            return t.NUM_REAL.toString();
        } else if(value.matches("([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*")) {
            return t.ID.toString();
        }

        for (int i=0; i < this.tabelaDeSimbolos[1].length; i++) {
            if(value.equals(this.tabelaDeSimbolos[1][i])) {
                tipo = this.tabelaDeSimbolos[0][i];
                return tipo;
            }
        }
        return t.ID.toString();
    }
}
