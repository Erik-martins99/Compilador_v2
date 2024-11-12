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
            t.OPIGUAL.toString(),
            t.OPMAIOR.toString(),
            t.OPMENOR.toString(),
            t.OPMAIORIGUAL.toString(),
            t.OPMENORIGUAL.toString(),
            t.OPNEG.toString(),
            t.OPDIFE.toString(),
            t.PV.toString(),
            t.AC.toString(),
            t.FC.toString(),
            t.OPATRIBUICAO.toString()},
            {"+", "-", "*", "/", "(", ")", "==",
            ">", "<", ">=", "<=", "!", "!=",";","{","}", "="}};


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
        } else if(value.equals("Paladin")) {
            return t.INTEGER.toString();
        } else if(value.equals("Druid")) {
            return t.DOUBLE.toString();
        } else if(value.equals("Sorcer")) {
            return t.STRING.toString();
        } else if(value.equals("Knight")) {
            return t.BOOLEAN.toString();
        } else if(value.equals("AND")) {
            return t.COND_AND.toString();
        } else if(value.equals("OR")) {
            return t.COND_OR.toString();
        } else if(value.equals("quest")) {
            return t.FUN_IF.toString();
        } else if(value.equals("peace")) {
            return t.FUN_ELSE.toString();
        } else if(value.equals("battle")) {
            return t.FUN_FOR.toString();
        } else if(value.equals("maze")) {
            return t.FUN_WHILE.toString();
        } else if(value.equals("invocation")) {
            return t.FUN_PRINT.toString();
        } else if(value.equals("kill")) {
            return t.BREAK.toString();
        } else if(value.equals("dungeon")) {
            return t.DEF_CLASS.toString();
        } else if(value.equals("mission")) {
            return t.DEF_FUNCTION.toString();
        } else if(value.equals("true")) {
            return t.VL_TRUE.toString();
        } else if(value.equals("false")) {
            return t.VL_FALSE.toString();
        } else if(value.equals("rogue")) {
            return t.RETURN.toString();
        } else if(value.equals("shedow")) {
            return t.VOID.toString();
        } else if(value.equals("mage")) {
            return t.STATIC.toString();
        } else if(value.equals("space")) {
            return t.NULL.toString();
        } else if(value.equals("skill")) {
            return t.CLASS_ATTR.toString();
        }
        else if(value.matches("[\\r\\n]+")) {
            return t.LINE_BREAK.toString();
        }
        else if(value.matches("\".*\"")) {
            return t.TXTSTRING.toString();
        } else if(value.matches("([a-z]|[A-Z])([a-z]|[A-Z]|[0-9])*")) {
            return t.ID.toString();
        }

        for (int i=0; i < this.tabelaDeSimbolos[1].length; i++) {
            if(value.equals(this.tabelaDeSimbolos[1][i])) {
                tipo = this.tabelaDeSimbolos[0][i];
                return tipo;
            }
        }
        return t.ERROR.toString();
    }
}
