/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tablero;

import app.fichas.Fichas;
import app.fichas.Escalera;
import app.fichas.Jugador;
import app.fichas.Serpiente;
import app.fichas.Vacia;

/**
 * Una de las clase principales del programa. 
 * Crea un tablero en base de un arreglo Tridimensional de objetos. Este 
 * arreglo esta estructurado en tablero[x][y][lugar] donde lugar es donde se guarda 
 * la posicion de las fichas por cada casilla.
 * 
 * @author Ricardo
 */
public class Tablero {
    protected Fichas tablero[][][];
    protected Jugador jugadores[];
    protected Escalera escaleras[];
    protected Serpiente serpientes[];
    protected int espacio;
    
    public Tablero(int numJugadores, int numSerpientes, int numEscaleras) {
        espacio = numJugadores + 1;
        tablero = new Fichas[8][8][espacio];
        escaleras = new Escalera[numEscaleras];
        serpientes = new Serpiente[numSerpientes];
        jugadores = new Jugador[numJugadores];
        vaciarTablero();
    }
    
    /**
     * Llena el tablero con Objetos de app.fichas.Vacia
     */
    private void vaciarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < espacio; k++) {
                    tablero[i][j][k] = new Vacia(i,j,k);
                }
            }
        }
    }
    
    /**
     * Obtiene un lugar especifico del tablero 
     * donde se encuentre una instacia de Vacio
     * 
     * @param x
     * @param y
     * @return int lugar con instancia Vacia
     */
    public int lugarVacio(int x, int y) {
        int lugar = -1;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Vacia) {
                lugar = i;
                break;
            }   
        }
        return lugar;
    }
    
    /**
     * Agrega un objeto jugador al tablero
     * 
     * @param nombre String
     * @param turno int
     */
    public void agregarJugador(String nombre, int turno) {
        int lugar = lugarVacio(7,0); // Revisar Posicion inicial del tablero por DATOS
        this.jugadores[turno] = new Jugador(nombre, lugar, 7, 0);
        tablero[7][0][lugar] = this.jugadores[turno];
    }
    
    /**
     * Agrega un Objeto escalera al tablero
     * 
     * @param xi posicion x inicial
     * @param yi posicion y inicial
     * @param xf posicion x final
     * @param yf posicion y final
     * @param turno 
     */
    public void agregarEscalera(int xi, int yi, int xf, int yf, int turno) {
        int lugar = lugarVacio(xi,yi);
        this.escaleras[turno] = new Escalera(xi, yi, xf, yf);
        tablero[xi][yi][lugar] = this.escaleras[turno];
    }
    
    /**
     * Agrega un Objeto serpiente al tablero
     * 
     * @param xi posicion x inicial
     * @param yi posicion y inicial
     * @param xf posicion x final
     * @param yf posicion y final
     * @param turno 
     */
    public void agregarSerpiente(int xi, int yi, int xf, int yf, int turno) {
        int lugar = lugarVacio(xi,yi);
        this.serpientes[turno] = new Serpiente(xi, yi, xf, yf);
        tablero[xi][yi][lugar] = this.serpientes[turno];
    }
    
    /**
     * Mueve el objeto Jugador[i] hacia otra casilla por 
     * la cantidad de movimientos
     * 
     * @param turno Numero de identificacion del jugador
     * @param movimientos Numero de movimientos a mover por el tablero
     */
     public void moverJugador(int turno, int movimientos) {
        int xVieja = jugadores[turno].getPosicionX();
        int yVieja = jugadores[turno].getPosicionY();
        int lVieja = jugadores[turno].getLugar();
        int lugar;
        jugadores[turno].mover(movimientos);
        lugar = lugarVacio(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY());
        jugadores[turno].setLugar(lugar);
        tablero[xVieja][yVieja][lVieja] = new Vacia(xVieja, yVieja, lVieja);
        tablero[jugadores[turno].getPosicionX()][jugadores[turno].getPosicionY()][lugar] = jugadores[turno];
    }

    public int getEspacio() {
        return espacio;
    }

    public Fichas[][][] getTablero() {
        return tablero;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public Escalera[] getEscaleras() {
        return escaleras;
    }

    public Serpiente[] getSerpientes() {
        return serpientes;
    }
}
