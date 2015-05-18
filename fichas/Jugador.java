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
public class Jugador extends Fichas {
    private String nombre;
    private int turno;
    
    public Jugador(String nombre, int lugar, int posicionX, int posicionY) {
        this.nombre = nombre;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.lugar = lugar;
        this.turno = lugar;
    }
    
    /**
     * Funcion mover Jugador. Mueve la ficha jugador a la siguiente casilla 
     * correspondiente a la forma del tablero.
     * 
     * Si posicionX es par se ejecutara un algoritmo propio.
     * Si posicionX es impar: se ejecutara un algoritmo diferente al par
     * 
     * @param movimientos 
     */
    public void mover(int movimientos) {
        if(posicionX % 2 != 0) {
            if(posicionY + movimientos > 7) {
                while(posicionY != 7) {
                    posicionY++;
                    movimientos--;
                }
                posicionY = 8-movimientos;
                posicionX--;
            } else {
                posicionY = posicionY + movimientos;
            }
        } else {
            if (!((posicionY - movimientos < 0) && (posicionX == 0))) {
                if(posicionY - movimientos < 0) {
                    while(posicionY != 0) {
                        posicionY--;
                        movimientos--;
                    }
                    posicionY = posicionY + movimientos -1;
                    posicionX--;
                } else {
                    posicionY = posicionY - movimientos;
                }
            } else { // Revisar eliminar despues de terminar el proyecto
                if(posicionY == 0) {
                    System.out.println("-ERROR: El Juego ya tuvo que terminar!!");
                } else {
                    System.out.println("No caiste en la casilla final!");
                }
            }
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getTurno() {
        return turno;
    }
    
    public void setTurno(int turno) {
        this.turno = turno;
    }

}
