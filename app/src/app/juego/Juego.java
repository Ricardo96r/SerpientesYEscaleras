/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.juego;

import java.util.Random;
import app.datos.Datos;
import app.datos.EscribirDatos;
import app.datos.LeerDatos;
import app.fichas.Jugador;
import app.fichas.Serpiente;
import app.fichas.Escalera;
import app.tablero.Tablero;

/**
 *
 * @author Ricardo
 */
public class Juego {
    private Pregunta pregunta = new Pregunta();
    private Jugador jugadores[];
    private int tablero[] = new int[4];
    private int serpientes[][];
    private int numSerpientes;
    private int escaleras[][];
    private int numEscaleras;
    private int numJugadores;
    private EscribirDatos datos;
    
    /**
     * Clase principal de la aplicacion
     */
    public void iniciarJuego() {
       preguntarDatos();
       Tablero tablero = new Tablero(numJugadores, numSerpientes, numEscaleras);
    }
    
    private void obtenerDatos() {
        LeerDatos datos = new LeerDatos();
        this.serpientes = datos.getDatosSerpientes();
        this.numSerpientes = serpientes.length;
        this.escaleras = datos.getDatosEscalera();
        this.numEscaleras = escaleras.length;
        this.tablero = datos.getDatosTablero();  
    }
    
    private void pedirDatosTablero() {
        System.out.println("- Insertar el comienzo y final del tablero en base a 8X8");
        System.out.println();
        tablero[0] = pregunta.preguntarPosicion("Insertar la posicion X Inicial del tablero");
        tablero[1] = pregunta.preguntarPosicion("Insertar la posicion Y Inicial del tablero");
        tablero[2] = pregunta.preguntarPosicion("Insertar la posicion X Final del tablero");
        tablero[3] = pregunta.preguntarPosicion("Insertar la posicion Y Final del tablero");
        datos.setPosicion(tablero[0],tablero[1],tablero[2],tablero[3]);
        datos.setLinea();
    }
    
    private void pedirDatosSerpientes() {
        numSerpientes = pregunta.preguntarNumero("¿Cuántas serpientes deseas agregar?");
        serpientes = new int[numSerpientes][4];
        for (int i = 0; i < numSerpientes; i++) {
            System.out.println("Agregar serpiente("+i+")");
            serpientes[i][0] = pregunta.preguntarPosicion("Insertar la posicion X Inicial de la serpiente("+i+")");
            serpientes[i][1] = pregunta.preguntarPosicion("Insertar la posicion Y Inicial de la serpiente("+i+")");
            serpientes[i][2] = pregunta.preguntarPosicion("Insertar la posicion X Final de la serpiente("+i+")");
            serpientes[i][3] = pregunta.preguntarPosicion("Insertar la posicion Y Final de la serpiente("+i+")");
        }
        for (int i = 0; i < numSerpientes; i++) {
            datos.setPosicion(serpientes[i][0],serpientes[i][1],serpientes[i][2],serpientes[i][3]);
            if(i != numSerpientes-1) {
                datos.setSeparador();
            }
        }
        datos.setLinea();
    }
    
    private void pedirDatosEscaleras() {
        numEscaleras = pregunta.preguntarNumero("¿Cuántas escaleras deseas agregar?");
        escaleras = new int[numEscaleras][4];
        for (int i = 0; i < numEscaleras; i++) {
            escaleras[i][0] = pregunta.preguntarPosicion("Insertar la posicion X Inicial de la escaleras("+i+")");
            escaleras[i][1] = pregunta.preguntarPosicion("Insertar la posicion Y Inicial de la escaleras("+i+")");
            escaleras[i][2] = pregunta.preguntarPosicion("Insertar la posicion X Final de la escaleras("+i+")");
            escaleras[i][3] = pregunta.preguntarPosicion("Insertar la posicion Y Final de la escaleras("+i+")");
        }
        for (int i = 0; i < numEscaleras; i++) {
            datos.setPosicion(escaleras[i][0],escaleras[i][1],escaleras[i][2],escaleras[i][3]);
            if(i != numSerpientes-1) {
                datos.setSeparador();
            }
        }
    }
    
    private void crearDatos() {
        EscribirDatos datos = new EscribirDatos();
        pedirDatosTablero();
        pedirDatosSerpientes();
        pedirDatosEscaleras();
        datos.close();
    }
    
    private void crearDatosAleatoriamente() {
        // Implementar luego
    }
    
    private void preguntarDatos() {
        LeerDatos datos = new LeerDatos();
        boolean respuesta[] = new boolean[2];
        
        if(datos.existeArchivo()) {
            respuesta[0] = pregunta.preguntarPorDosOpciones("¿Quieres comenzar el juego con los datos guardados anteriormente?");
        } else {
            System.out.println("- No se ha detectado ningun archivo de guardado llamado "+datos.getArchivo());
            System.out.println();
            respuesta[0] = false;
        }
        
        if(respuesta[0]) {
            obtenerDatos();
        } else {
            respuesta[1] = pregunta.preguntarPorDosOpciones("¿Quieres crear los datos del juego tu mismo? Si no es asi se crearan aleatoriamente.");
            if(respuesta[1]) {
                crearDatos();
            } else {
                crearDatosAleatoriamente();
            }
        }
    }
}
