/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.tablero;

import app.consola.InterfazConsola;
import app.fichas.Escalera;
import app.fichas.Jugador;
import app.fichas.Serpiente;

/**
 * Clase que crea el tablero por consola una 
 * Matriz de String, usando por padre la clase Tablero que contiene una matriz 
 * tridimensional de objetos (Fichas). Esta clase solo transforma una matriz de 
 * objetos en una matriz de String visible por el usuario
 * 
 * @author Ricardo
 */
public class TableroConsola extends Tablero {
    private String tableroConsola[][];
    private InterfazConsola interfaz = new InterfazConsola();

    public TableroConsola(int numJugadores, int numSerpientes, int numEscaleras, int tableroDatos[]) {
        super(numJugadores, numSerpientes, numEscaleras, tableroDatos);
        tableroConsola = new String[8][8];
    }
    
    /**
     * Mostrar el Caracter especifico de la serpiente
     * @return String
     */
    private String mostrarSerpiente() {
        return interfaz.setColorSerpiente("$");
    }
    
    /**
     * Mostrar el caracter especifico de la escalera
     * @return String
     */
    private String mostrarEscalera() {
        return interfaz.setColorEscalera("#");
    }
    
    /**
     * Mostrar el caracter especifico por jugador
     * @param lugar
     * @return String
     */
    private String mostrarJugador(int lugar) {
        return (char)27+"[31mJ"+(lugar+1)+(char)27+"[0m";
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
     * Agrega color a la casilla final y la casilla inicial
     * 
     * @param x posicion x
     * @param y posicion y
     * @param casilla numero en String
     * @return String con color
     */
    private String colorTableroDatos(int x, int y, String casilla) {
        String cadena = "";
        if (x % 2 == 0) {
            y = 7-y;
        }
        if(x == tableroDatos[0] && y == tableroDatos[1]) {
            cadena = interfaz.setColorInicioTablero(casilla);
        } else {
            if (x == tableroDatos[2] && y == tableroDatos[3]) {
                cadena = interfaz.setColorFinalTablero(casilla);
            } else {
                cadena = casilla;
            }
        }
        return cadena;
    }
    
    /**
     * Agrega los numeros del 1-64 al tablero
     */
    private void numerosTablero() {
        int contador = 1;
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 != 0) {
                    tableroConsola[i][j] = colorTableroDatos(i,j,Integer.toString(contador));
                    contador++;
                } else {
                    tableroConsola[i][7-j] = colorTableroDatos(i,j,Integer.toString(contador));
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
