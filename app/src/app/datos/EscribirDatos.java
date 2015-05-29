/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.datos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ricardo
 */
public class EscribirDatos extends Datos {
    
    private FileWriter fWDatos;
    private PrintWriter escribirDatos;
    
    /**
     * Constructor de la clase
     */
    public EscribirDatos() {
        super("datos.txt");
        try {
            fWDatos = new FileWriter(archivo);
        } catch (IOException ex) {
            System.out.println("-ERROR: al escribir archivo: "+ex.getMessage());
        }
        escribirDatos = new PrintWriter(fWDatos);
    }
    
    /**
     *  Ordena los datos posicion para el archivo de texto
     */
    private String ordenarDatosPosicion(int xi, int yi, int xf, int yf) {
        return "<"+xi+","+yi+">,<"+xf+","+yf+">";
    }
    
    /**
     * Agrega los datos posicion al archivo
     */
    private void crearDatosPosicion(int xi, int yi, int xf, int yf) {
        escribirDatos.append(ordenarDatosPosicion(xi, yi, xf, yf));
    }
    
    /**
     * Revisa si es posible agregar el comienzo y final del tablero correctamente
     * 
     * @param xi comienzo del juego x int
     * @param yi comienzo del juego y int
     * @param xf final del juego x int 
     * @param yf final del juego y int
     * @return 
     */
    public boolean validarDatosTablero(int xi, int yi, int xf, int yf) {
        boolean validado = false;
        if (xi == xf) {
            if (xi % 2 != 0) {
                if (yi < yf) {
                    validado = true;
                }
            } else {
                if(yi > yf) {
                    validado = true;
                }
            }
        } else {
            if (xi > xf) {
                validado = true;
            }
        }
        return validado;
    }
    
    /**
     * Valida si existe o no una serpiente en una casilla especificada
     * 
     * @param x
     * @param y
     * @param serpientes Los datos de las serpientes existentes
     * @param numSerpiente Sirve para saber cuantas serpientes se han agregado
     * @return 
     */
    public boolean existeSerpiente(int x, int y, int[][] serpientes, int numSerpiente) {
        boolean validacion = false;
        for (int i = 0; i < numSerpiente; i++) {
            if (x == serpientes[i][0] && y == serpientes[i][1]) {
                validacion = true;
                break;
            }
        }
        return validacion;
    }
    
    /**
     * Valida si existe o no una escalera en una casilla especificada
     * 
     * @param x
     * @param y
     * @param serpientes Los datos de las serpientes existentes
     * @param numSerpiente Sirve para saber cuantas serpientes se han agregado
     * @return 
     */
    public boolean existeEscalera(int x, int y, int[][] escaleras, int numEscalera) {
        boolean validacion = false;
        for (int i = 0; i < numEscalera; i++) {
            if (x == escaleras[i][0] && y == escaleras[i][1]) {
                validacion = true;
                break;
            }
        }
        return validacion;
    }
    
    /**
     * Valida los datos de la serpiente para saber si se pusieron correctamente
     * Validacion:
     *      -Valida si la serpiente se puede colocar en el espacio asignado por el tablero (Inicio-fin)
     *      -Valida si xi >= xf
     * 
     * @param xi inicial x
     * @param yi inicial y
     * @param xf final x
     * @param yf final y
     * @param tablero Son las posiciones del tablero
     * @return validacion boolean true si pasa la validacion
     */
    public boolean validarDatosSerpiente(int xi, int yi, int xf, int yf, int[] tablero) {
        boolean validacion = true;
        if((xi == tablero[2] && yi == tablero[3]) || (xi < tablero[2]) || (xf > tablero[0]) || (xf == tablero[0] && yf == tablero[1]) || (xi >= xf)) {
            validacion = false;
        }
        return validacion;
    }
    
    /**
     * Valida los datos de la escalera para saber si se pusieron correctamente
     * Validacion:
     *      -Valida si la serpiente se puede colocar en el espacio asignado por el tablero (Inicio-fin)
     *      -Valida si existe una serpiente en esa posicion
     *      -Valida si xi >= xf
     * 
     * @param xi inicial x
     * @param yi inicial y
     * @param xf final x
     * @param yf final y
     * @param serpientes objeto serpientes de las serpientes agregadas anteriormente
     * @return validacion boolean true si pasa la validacion
     */
    public boolean validarDatosEscalera(int xi, int yi, int xf, int yf, int[] tablero) {
        boolean validacion = true;
        if((xi == tablero[2] && yi == tablero[3]) || (xi < tablero[2]) || (xf > tablero[0]) || (xf == tablero[0] && yf == tablero[1]) || (xi <= xf)) {
            validacion = false;
        }
        return validacion;
    }
    
    public boolean existeArchivo() {
        File archivo = new File(this.archivo);
        return archivo.exists();
    }
    
    /**
     * Cerrar archivo
     */
    public void close() {
        escribirDatos.close();
    }
    
    public void setPosicion(int xi, int yi, int xf, int yf) {
        crearDatosPosicion(xi, yi, xf, yf);
    }
    
    public void setSeparador() {
        escribirDatos.append("|");
    }
    
    public void setLinea() {
        escribirDatos.append("\r\n");
    }

}
