/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.datos;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class LeerDatos extends Datos {
    private int[] tablero = new int[4];
    private int Serpiente[][];
    
    /**
     * Cantidad de caracteres que tiene el archivo de datos actualmente
     * 
     * @return int cantidad de caracteres
     */
    private int cantidadDeCaracteresDelArchivo() {
        int valor;
        int cantidadChar = 0;
        try {
            FileReader buscar = new FileReader(archivo);
            valor = buscar.read();
            while(valor != -1) {
                cantidadChar++;
                valor = buscar.read();
            }
        buscar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadChar;
    }
    
    /**
     * Devuelve el String de la linea pedida
     * 
     * @param numLinea numero de linea [1,infinito)
     * @return String linea
     */
    private String obtenerLinea(int numLinea) {
        int cantidadChar = cantidadDeCaracteresDelArchivo();
        char datos[] = new char[cantidadChar];
        int valor;
        String[] datosLineas;
        try {
            FileReader buscar = new FileReader(archivo);
            valor = buscar.read();
            int posicion = 0;
            while(valor != -1) {
                datos[posicion] = (char)valor;
                posicion++;
                valor = buscar.read();
            }
            buscar.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeerDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuffer resultado = new StringBuffer();
        for (int i = 0; i < cantidadChar; i++) {
           resultado.append( datos[i] );
        }
        String datosString = resultado.toString();
        datosLineas = datosString.split("\r\n");
        return datosLineas[numLinea-1];
    }
    
    /**
     * Separa los datos de una linea y los pasa a una matriz
     * 
     * @param numLinea numero de linea
     * @return datos
     */
    private int[][] separarDatos(int numLinea) {
        String linea = obtenerLinea(numLinea);
        String datosPosicion[] = linea.split("\\|");
        int posicion = 0;
        String datosI[][] = new String[datosPosicion.length][];
        int datos[][] = new int[datosPosicion.length][10];
        
        for (int i = 0; i < datosPosicion.length; i++) {
            datosI[i] = datosPosicion[i].split("<|>|,");
        }
        for (int i = 0; i < datosI.length; i++) {
            for (int j = 0; j < datosI[i].length; j++) {
                if (!datosI[i][j].isEmpty()) {
                   datos[i][posicion] = Integer.parseInt(datosI[i][j]);
                   posicion++;
                }
            }
            posicion = 0;
        }
        return datos;
    }
    
    /**
     * Tablero en la linea 1 con posicion 0.
     * 
     * @return matriz de datos 
     */
    public int[] getDatosTablero() {
        return separarDatos(1)[0];
    }
    
    /**
     * Serpientes en la linea 2
     * 
     * @return matriz de datos 
     */
    public int[][] getDatosSerpientes() {
        return separarDatos(2);
    }
    
    /**
     * Escaleras en la linea 3
     * 
     * @return matriz de datos 
     */
    public int[][] getDatosEscalera() {
        return separarDatos(3);
    }
    
}
