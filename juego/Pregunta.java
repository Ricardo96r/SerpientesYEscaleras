/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.juego;

import java.util.Scanner;

/**
 *
 * @author Ricardo
 */
public class Pregunta {
    public static Scanner escanear = new Scanner(System.in);
    
    public boolean preguntarPorDosOpciones(String pregunta) {
        String opcion1 = "S";
        String opcion2 = "N";
        boolean validado = false;
        String resp = "";
        boolean respuesta;
        pregunta += " ("+opcion1.toUpperCase();
        pregunta +="/"+opcion2.toUpperCase()+")";
        while(!validado) {
            System.out.print("- "+pregunta+": ");
            resp = escanear.next();
            if(!(resp.length() > 1)) {
                if(resp.equalsIgnoreCase(opcion1) || resp.equalsIgnoreCase(opcion2)) {
                    validado = true;
                } else {
                    validado = false;
                    System.out.println("- ERROR: El caracter insertado no es valido!");
                    System.out.println();
                }
            } else {
                validado = false;
                System.out.println("- ERROR: Solo se admite un caracter!");
                System.out.println();
            }
        }
        if (resp.equalsIgnoreCase(opcion1)) {
            respuesta = true;
        } else {
            respuesta = false;
        }
        System.out.println();
        return respuesta;
    }
    
    public int preguntarPosicion(String pregunta) {
        boolean validacion  = false;
        int posicion = 0;
        while(!validacion) {
            System.out.print("- "+pregunta+": ");
            try {
            posicion = escanear.nextInt();
            if (posicion < 0 || posicion > 7) {
                validacion = false;
                System.out.println("- ERROR: La posicion no es valida para un tablero de tamaño 8x8");
                System.out.println();
            } else {
                validacion = true;
            }
            } catch (Exception e) {
                System.out.println("- ERROR: Solo numeros se permiten");
                System.out.println();
                escanear.next();
                validacion = false;
            }
        }
         System.out.println();
        return posicion;
    }
    
    public int preguntarNumero(String pregunta) {
        boolean validacion  = false;
        int posicion = 0;
        while(!validacion) {
            System.out.print("- "+pregunta+": ");
            try {
            posicion = escanear.nextInt();
            validacion = true;
            } catch (Exception e) {
                System.out.println("- ERROR: Solo numeros se permiten");
                System.out.println();
                escanear.next();
                validacion = false;
            }
        }
        System.out.println();
        return posicion;
    }
}
