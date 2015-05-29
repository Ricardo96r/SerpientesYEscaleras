/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.fichas;

/**
 * Ficha vacia. Esta clase vacia es importante cuando no existe un objeto Ficha 
 * en la matriz del tablero
 * @author Ricardo
 */
public class Vacia extends Fichas {
    public Vacia(int posicionX, int posicionY, int lugar) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.lugar = lugar;
    }
}
