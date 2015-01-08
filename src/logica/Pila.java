package logica;

import logica.movimiento.Movimiento;

/**
 * Representacion funcional de una pila de elementos. Apila los elementos de tal
 * forma que el ultimo elemento que entra es el primero que sale.
 */
public class Pila {

    private Movimiento[] undoStack;
    private int numUndo;

    /**
     * Constructora por defecto. Asigna valores por defecto a los atributos.
     * Valor 10 a la dimension de undoStack. Valor 0 a numUndo.
     */
    public Pila() {
        this.undoStack = new Movimiento[10];
        this.numUndo = 0;
    }

    public int getNumUndo() {
        return this.numUndo;
    }

    public int tamanno() {
        return undoStack.length;
    }

    /**
     * Desapila un elemento. Devuelve el ultimo elemento almacenado en undoStack
     * y decrementa el valor de numUndo. Si no hay elementos en la pila,
     * devuelve null.
     *
     * @return ultimo elemento apilado.
     */
    public Movimiento desapilar() {
        if (this.numUndo > 0) {
            this.numUndo--;
            return this.undoStack[this.numUndo];
        } else {
            return null;
        }
    }

    /**
     * Apila un elemento. Almacena en la primera posicion libre de undoStack el
     * elemento indicado. El valor del numero elementos en la pila aumenta. Si
     * la pila ya esta llena, llama al metodo redimensionar.
     *
     * @param elem elemento a apilar
     */
    public void apilar(Movimiento elem) {
        if (numUndo >= undoStack.length) {
            this.redimensionar();
        }

        this.undoStack[this.numUndo] = elem;
        this.numUndo++;

    }

    /**
     * Vuelve a apuntar al inicio del array por lo que se comienza a apilar de
     * nuevo los elementos.
     */
    public void reset() {
        this.numUndo = 0;
    }

    /**
     * Redimensiona el array para que admita el doble de lementos que su
     * capacidad.
     */
    private void redimensionar() {

        Movimiento aux[] = new Movimiento[this.numUndo];
        System.arraycopy(this.undoStack, 0, aux, 0, this.numUndo);
        int size = this.undoStack.length * 2;
        this.undoStack = new Movimiento[size];
        System.arraycopy(aux, 0, this.undoStack, 0, this.numUndo);

    }
}
