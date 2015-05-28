/*
 * Ã¯Â»Â¿
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.fichas;

/**
 *
 * @author Ricardo
 */
public class Serpiente extends Fichas {
    private int irX, irY;
    
    public Serpiente(int posicionX, int posicionY, int irX, int irY) {
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
