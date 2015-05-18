/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tablero;

import app.fichas.Escalera;
import app.fichas.Jugador;
import app.fichas.Serpiente;

/**
 * Clase que crea la interfaz de usuario por consola del tablero por una 
 * Matriz de String. Usando por padre la clase Tablero que es imprecindible.
 * 
 * @author Ricardo
 */
public class TableroConsola extends Tablero {
    private String tableroConsola[][];

    public TableroConsola(int numJugadores, int numSerpientes, int numEscaleras) {
        super(numJugadores, numSerpientes, numEscaleras);
        tableroConsola = new String[8][8];
    }
    
    /**
     * Mostrar el Caracter especifico de la serpiente
     * @return String
     */
    private String mostrarSerpiente() {
        return "$";
    }
    
    /**
     * Mostrar el caracter especifico de la escalera
     * @return String
     */
    private String mostrarEscalera() {
        return "#";
    }
    
    /**
     * Mostrar el caracter especifico por jugador
     * @param lugar
     * @return String
     */
    private String mostrarJugador(int lugar) {
        return "J"+(lugar+1);
    }
    
    /**
     * Separa la cantidad de jugadores que hay en una casilla por comas
     * 
     * @param x 
     * @param y
     * @return cadena de jugadores separada por comas si es que existen.
     */
    private String separarJugadoresPorCasilla(int x, int y) {
        Jugador jugadoresCasilla[] = new Jugador[cantidadJugadoresCasilla(x,y)];
        int contador = 0;
        String cadena = "";
        for (int i = 0; i < espacio; i++) {
            if (tablero[x][y][i] instanceof Jugador) {
                jugadoresCasilla[contador] = (Jugador) tablero[x][y][i];
                contador++;
            }
        }
        for (int i = 0; i < jugadoresCasilla.length; i++) {
            cadena += mostrarJugador(jugadoresCasilla[i].getTurno());
            if (i < contador-1) {
                cadena += ",";
            }
        }
        return cadena;
    }
    
  
    /**
     * Saber si existe un jugador o varios.
     * 
     * @param x
     * @param y
     * @return existen boolean
     */
    private int cantidadJugadoresCasilla(int x, int y) {
        int cantidad = 0;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Jugador) {
                cantidad++;
            }
        }
        return cantidad;
    }
   
    /**
     * Agrega los numeros del 1-64 al tablero
     */
    private void numerosTablero() {
        int contador = 1;
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    tableroConsola[i][j] = Integer.toString(contador);
                    contador++;
                } else {
                    tableroConsola[i][7-j] = Integer.toString(contador);
                    contador++;
                }
            }
        }
    }
    
    /**
     * Para saber si existen serpientes o escaleras en la casilla
     * 
     * @param x
     * @param y
     * @return existe boolean
     */
    private boolean existeSerpienteOEscalera(int x, int y) {
        boolean existe = false;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Serpiente) {
                existe = true;
            } else if(tablero[x][y][i] instanceof Escalera) {
                existe = true;
            }
        }
        return existe;
    }
    
    /**
     * Agrega la serpiente o la escalera en la casilla correspondiente
     * 
     * @param x
     * @param y
     * @return String
     */
    private String agregarSerpienteOEscalera(int x, int y) {
        String cadena = null;
        for (int i = 0; i < espacio; i++) {
            if(tablero[x][y][i] instanceof Serpiente) {
                cadena = mostrarSerpiente();
            } else if(tablero[x][y][i] instanceof Escalera) {
                cadena = mostrarEscalera();
            }
        }
        return cadena;
    }
    
    /**
     * Funcion principal de la clase. Muestra el tablero por la consola.
     */
    public void mostrarTableroPorConsola() {
        numerosTablero();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(cantidadJugadoresCasilla(i, j) > 0) {
                    tableroConsola[i][j] += separarJugadoresPorCasilla(i,j);
                }
                if(existeSerpienteOEscalera(i,j)) {
                    tableroConsola[i][j] += agregarSerpienteOEscalera(i,j);
                }
                System.out.print(tableroConsola[i][j]+"\t");
            }
            System.out.println();
        }
    }
    
}
