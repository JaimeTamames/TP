package exceptions;

public class ColumnaIncorrecta extends MovimientoInvalido {

    public ColumnaIncorrecta() {
        super();
    }

    public ColumnaIncorrecta(String s) {
        super(s);
    }

    public ColumnaIncorrecta(int c) {
        super("Valores entre '1' y '" + c + "'");
    }
}
