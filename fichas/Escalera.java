/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.fichas;

import app.fichas.Fichas;

/**
 * Clase que maneja las escaleras del juego como sus reglas y funcion
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
