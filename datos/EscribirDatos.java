/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.datos;

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
