package exceptions;

public class ColumnaLlena extends MovimientoInvalido {

    public ColumnaLlena() {
        super();
    }

    public ColumnaLlena(String s) {
        super(s);
    }

    public ColumnaLlena(int c) {
        super("La columna '" + (c + 1) + "' esta llena");
    }
}
