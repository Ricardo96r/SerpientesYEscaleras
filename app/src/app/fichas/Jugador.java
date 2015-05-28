/*
 * Ã¯Â»Â¿
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.fichas;

import app.consola.InterfazConsola;

/**
 * Clase jugador hereda de fichas.
 * @author Ricardo
 */
public class Jugador extends Fichas {
    private String nombre;
    private int turno;
    private int tableroXI;
    private int tableroYI;
    private int tableroXF;
    private int tableroYF;
    private int contadorSeis;
    private InterfazConsola interfaz = new InterfazConsola();
    
    public Jugador(String nombre, int lugar, int posicionX, int posicionY, int tableroXF, int tableroYF) {
        this.nombre = nombre;
        this.posicionX = tableroXI = posicionX;
        this.posicionY = tableroYI = posicionY;
        this.lugar = lugar;
        this.turno = lugar;
        this.tableroXF = tableroXF;
        this.tableroYF = tableroYF;
    }
    
    /**
     * Revisa si el dado cayo tres veces seis
     * @param movimientos 
     */
    private void dadoTresVecesSeis(int movimientos) {
        if (contadorSeis != 3) {
            if (movimientos == 6) {
                contadorSeis++;
                if(contadorSeis == 3) {
                    posicionX = tableroXI;
                    posicionY = tableroYI;
                    interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" ha sacado 3 veces seguidas 6. Se le enviara al inicio del tablero.");
                }
            } else {
                contadorSeis = 0;
            }
        } else {
            if (movimientos != 6) {
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" no ha sacado un 6. Por lo cual no se mueve. Ya que habia sacado 3 veces 6");
                posicionX = tableroXI;
                posicionY = tableroYI;
            } else {
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" ha sacado un 6. Ahora se puede mover");
                contadorSeis = 0;
            }
        }
    }
    
    /**
     * Funcion mover Jugador. Mueve la ficha jugador a la siguiente casilla 
     * correspondiente a la forma del tablero.
     * 
     * Si posicionX es par se ejecutara un algoritmo propio.
     * Si posicionX es impar: se ejecutara un algoritmo diferente al par
     * 
     * @param movimientos 
    }
    */
    public void mover(int movimientos) {
        int posicionAnteriorX = posicionX, posicionAnteriorY = posicionY, movimientosAnterior = movimientos;
        if(posicionX % 2 != 0) {
            if (!((posicionY + movimientos > tableroYF) && (posicionX == tableroXF))) {
                if(posicionY + movimientos > 7) {
                    while(posicionY != 7) {
                        posicionY++;
                        movimientos--;
                    }
                    posicionY = 8-movimientos;
                    posicionX--;
                    if (tableroXF == posicionX && tableroYF > posicionY) {
                        posicionY = posicionAnteriorY;
                        posicionX = posicionAnteriorX;
                        interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" se ha pasado de la casilla final, por lo cual no se mueve");
                    }
                } else {
                    posicionY = posicionY + movimientos;
                }
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" se ha movido "+movimientosAnterior+" movimientos(s)");
            } else {
                 interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" se ha pasado de la casilla final, por lo cual no se mueve");
            }
        } else {
            if (!((posicionY - movimientos < tableroYF) && (posicionX == tableroXF))) {
                if(posicionY - movimientos < 0) {
                    while(posicionY != 0) {
                        posicionY--;
                        movimientos--;
                    }
                    posicionY = posicionY + movimientos -1;
                    posicionX--;
                    if (tableroXF == posicionX && tableroYF < posicionY) {
                        posicionY = posicionAnteriorY;
                        posicionX = posicionAnteriorX;
                        interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" se ha pasado de la casilla final, por lo cual no se mueve");
                    }
                } else {
                    posicionY = posicionY - movimientos;
                }
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" se ha movido "+movimientosAnterior+" movimientos(s)");
            } else { // Revisar eliminar despues de terminar el proyecto
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+nombre+" se ha pasado de la casilla final, por lo cual no se mueve");
            }
        }
        dadoTresVecesSeis(movimientosAnterior);
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
