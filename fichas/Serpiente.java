/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
