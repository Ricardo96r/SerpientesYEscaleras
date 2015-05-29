/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.fichas;

/**
 * Clase que maneja las escaleras del juego
 * 
 * @author Ricardo
 */
public class Escalera extends Fichas {
    private int irX, irY;
    
    public Escalera(int posicionX, int posicionY, int irX, int irY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.irX = irX;
        this.irY = irY;
    }
    
    public int getIrX() {
        return irX;
    }

    public int getIrY() {
        return irY;
    }
}
