/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.consola;

import java.util.Scanner;

/**
 * Clase que contiene funciones de preguntas muy seguidas con sus validaciones
 * para mostrar por consola. Tambien se encuentran funciones void que agregan 
 * al String pasado por parametro colores
 * 
 * @author Ricardo
 */
public class InterfazConsola {
    public static Scanner escanear = new Scanner(System.in);
    private final String limpiarConsola = (char)27+"[0m";
    
    /**
     * Pregunta por dos opciones validadas. Si o No
     * 
     * @param pregunta String 
     * @return true si es "S" o false si es "N"
     */
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
                    mostrarError("El caracter insertado no es valido!");
                }
            } else {
                validado = false;
                mostrarError("Solo se admite un caracter!");
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
    
    /**
     * Pregunta un numero validado por la posicion del tablero
     * 
     * @param pregunta
     * @return int posicion preguntada
     */
    public int preguntarPosicion(String pregunta) {
        boolean validacion  = false;
        int posicion = 0;
        while(!validacion) {
            System.out.print("- "+pregunta+": ");
            try {
            posicion = escanear.nextInt();
            if (posicion < 0 || posicion > 7) {
                validacion = false;
                mostrarError("La posicion no es valida para un tablero de tamaño 8x8");
            } else {
                validacion = true;
            }
            } catch (Exception e) {
                mostrarError("Solo numeros se permiten");
                escanear.next();
                validacion = false;
            }
        }
         System.out.println();
        return posicion;
    }
    
    /**
     * Pregunta cualquier numero positivo validado
     * 
     * @param pregunta
     * @return 
     */
    public int preguntarNumero(String pregunta) {
        boolean validacion  = false;
        int posicion = 0;
        while(!validacion) {
            System.out.print("- "+pregunta+": ");
            try {
            posicion = escanear.nextInt();
            validacion = true;
            if (posicion < 0) {
                validacion = false;
                mostrarError("Solo numeros positivos");
            }
            } catch (Exception e) {
                mostrarError("Solo numeros se permiten");
                escanear.next();
                validacion = false;
            }
        }
        System.out.println();
        return posicion;
    }
    
    /**
     * Pregunta por un String. Errores con los espacios.
     * @param pregunta
     * @return 
     */
    public String preguntarCadena(String pregunta) {
        String cadena;
        System.out.print("- "+pregunta+": ");
        cadena = escanear.next();
        System.out.println();
        return cadena;
    }
    
    public void mostrarTitulo() {
        System.out.println((char)27+"[37;42m SERPIENTES Y ESCALERAS "+limpiarConsola);
        System.out.println();
    }
    
    public void mostrarEnunciado(String cadena) {
        System.out.println((char)27+"[47m "+cadena+" "+limpiarConsola);
    }
    
    public void mostrarFase(String cadena) {
        System.out.println((char)27+"[37;42m FASE "+(char)27 +"[30;47m "+cadena+" "+limpiarConsola);
        System.out.println();
    }
    
    public void mostrarError(String error) {
        System.out.println((char)27+"[37;41m ERROR "+(char)27 +"[30;47m"+ " "+error+" "+limpiarConsola);
        System.out.println();
    }
    
    public void mostrarAviso(String aviso) {
        System.out.println((char)27+"[37;43m AVISO "+(char)27 +"[30;47m"+ " "+aviso+" "+limpiarConsola);
        System.out.println();
    }
    
    public void mostrarOpcionMenu(String cadena, int numero) {
        System.out.println((char)27+"[34m"+numero+"- "+limpiarConsola+cadena);
    }
    
    public void mostrarRegla(String cadena) {
        System.out.println("<> "+cadena);
    }

    public String setColorSerpiente(String cadena) {
        return (char)27+"[37;44m"+cadena+limpiarConsola;
    }
    
    public String setColorEscalera(String cadena) {
        return (char)27+"[43m"+cadena+limpiarConsola;
    }
    
    public String setColorInicioTablero(String cadena) {
        return (char)27+"[41m"+cadena+limpiarConsola;
    }
    
    public String setColorFinalTablero(String cadena) {
        return (char)27+"[42m"+cadena+limpiarConsola;
    }
}
