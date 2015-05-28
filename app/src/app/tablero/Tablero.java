/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tablero;

import app.consola.InterfazConsola;
import app.fichas.Fichas;
import app.fichas.Escalera;
import app.fichas.Jugador;
import app.fichas.Serpiente;
import app.fichas.Vacia;

/** 
 * Crea un tablero en base de un arreglo Tridimensional de objetos. Este 
 * arreglo esta estructurado en tablero[x][y][lugar] donde lugar es donde se guarda 
 * la posicion de las fichas por cada casilla. Donde lugar tiene un 
 * espacio = numero de jugadores + 1. El +1 es relativo ya que puede existe 
 * una escalera o una serpiente.
 * 
 * @author Ricardo
 */
public class Tablero {
    protected Fichas tablero[][][];
    protected int tableroDatos[];
    protected Jugador jugadores[];
    protected Escalera escaleras[];
    protected Serpiente serpientes[];
    protected int espacio;
    private InterfazConsola interfaz = new InterfazConsola();
    
    public Tablero(int numJugadores, int numSerpientes, int numEscaleras, int tableroDatos[]) {
        espacio = numJugadores + 1;
        tablero = new Fichas[8][8][espacio];
        escaleras = new Escalera[numEscaleras];
        serpientes = new Serpiente[numSerpientes];
        jugadores = new Jugador[numJugadores];
        this.tableroDatos = tableroDatos;
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
    private int lugarVacio(int x, int y) {
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
        int xi = tableroDatos[0];
        int yi = tableroDatos[1];
        int xf = tableroDatos[2];
        int yf = tableroDatos[3];
        int lugar = lugarVacio(xi,yi);
        this.jugadores[turno] = new Jugador(nombre, lugar, xi, yi, xf, yf);
        tablero[xi][yi][lugar] = this.jugadores[turno];
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
        if (!((jugadores[turno].getPosicionX() == xVieja) && (jugadores[turno].getPosicionY() == yVieja))) {
            if(existeEscalera(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY())) {
                jugadores[turno].setPosicion(moverJugadorEscalera(jugadores[turno].getPosicionX(),jugadores[turno].getPosicionY()));
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+ jugadores[turno].getNombre()+
                                      " ha caido en una escalera y ha subido a la posicion: "
                                      +jugadores[turno].getPosicionX()+", "+jugadores[turno].getPosicionY());
            }
            if(existeSerpiente(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY())) {
                jugadores[turno].setPosicion(moverJugadorSerpiente(jugadores[turno].getPosicionX(),jugadores[turno].getPosicionY()));
                interfaz.mostrarAviso("El jugador("+(turno+1)+") llamado "+ jugadores[turno].getNombre()+
                                      " ha caido en una serpiente y ha bajado a la posicion: "
                                      +jugadores[turno].getPosicionX()+", "+jugadores[turno].getPosicionY());
            }
            lugar = lugarVacio(jugadores[turno].getPosicionX(), jugadores[turno].getPosicionY());
            jugadores[turno].setLugar(lugar);
            tablero[xVieja][yVieja][lVieja] = new Vacia(xVieja, yVieja, lVieja);
            tablero[jugadores[turno].getPosicionX()][jugadores[turno].getPosicionY()][lugar] = jugadores[turno];
        }
    }
    
    /**
     * Saber si existe una serpiente en esa casilla
     * 
     * @param x Posicion x de la casilla
     * @param y Posicion y de la casilla
     * @return boolean verdadero si existe
     */
    private boolean existeSerpiente(int x, int y) {
        boolean existe = false;
        for (int i = 0; i < serpientes.length; i++) {
            if (serpientes[i].getPosicionX() == x && serpientes[i].getPosicionY() == y) {
                existe = true;
            }
        }
        return existe;
    }
    
    /**
     * Retorna el valor de la cola de la serpiente
     * 
     * @param x Posicion x de la casilla
     * @param y Posicion y de la casilla
     * @return int[] posicion de la cola de la serpiente
     */
    private int[] moverJugadorSerpiente(int x, int y) {
        int posicion[] = new int[2];
        for (int i = 0; i < serpientes.length; i++) {
            if (serpientes[i].getPosicionX() == x && serpientes[i].getPosicionY() == y) {
                posicion[0] = serpientes[i].getIrX();
                posicion[1] = serpientes[i].getIrY();
            }
        }
        return posicion;
    }
    
    /**
     * Saber si existe una escalera en esa casilla
     * 
     * @param x Posicion x de la casilla
     * @param y Posicion y de la casilla
     * @return boolean verdadero si existe
     */
    private boolean existeEscalera(int x, int y) {
        boolean existe = false;
        for (int i = 0; i < escaleras.length; i++) {
            if (escaleras[i].getPosicionX() == x && escaleras[i].getPosicionY() == y) {
                existe = true;
            }
        }
        return existe;
    }
    
    /**
     * Retorna el valor final de la escalera
     * 
     * @param x Posicion x de la casilla
     * @param y Posicion y de la casilla
     * @return int[] posicion final de la escalera
     */
    private int[] moverJugadorEscalera(int x, int y) {
        int posicion[] = new int[2];
        for (int i = 0; i < escaleras.length; i++) {
            if (escaleras[i].getPosicionX() == x && escaleras[i].getPosicionY() == y) {
                posicion[0] = escaleras[i].getIrX();
                posicion[1] = escaleras[i].getIrY();
            }
        }
        return posicion;
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