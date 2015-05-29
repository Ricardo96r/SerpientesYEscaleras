/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.datos;

import app.fichas.Jugador;
import app.consola.InterfazConsola;

/**
 *
 * @author Ricardo
 */
public class EscribirDatosConsola extends EscribirDatos {
    private InterfazConsola interfaz = new InterfazConsola();
    private int tablero[] = new int[4];
    private int serpientes[][];
    private int numSerpientes;
    private int escaleras[][];
    private int numEscaleras;
    
    /**
     * Pide el comienzo y final del tablero validados
     */
    private void pedirDatosTablero() {
        boolean validado;
        do {
            interfaz.mostrarFase("Tablero");
            tablero[0] = interfaz.preguntarPosicion("Insertar la posicion X Inicial del tablero");
            tablero[1] = interfaz.preguntarPosicion("Insertar la posicion Y Inicial del tablero");
            tablero[2] = interfaz.preguntarPosicion("Insertar la posicion X Final del tablero");
            tablero[3] = interfaz.preguntarPosicion("Insertar la posicion Y Final del tablero");
            validado = validarDatosTablero(tablero[0], tablero[1], tablero[2], tablero[3]);
            if(!validado) {
                interfaz.mostrarError("Los datos del tablero no pueden ser posibles. Se repetira.");
            }
        } while(!validado);
        setPosicion(tablero[0],tablero[1],tablero[2],tablero[3]);
        setLinea();
    }
    
    /**
     * Pide los datos por consola de las serpientes validados
     */
    private void pedirDatosSerpientes() {
        boolean validado;
        interfaz.mostrarFase("Serpientes");
        numSerpientes = interfaz.preguntarNumero("¿Cuántas serpientes deseas agregar?");
        serpientes = new int[numSerpientes][4];
        for (int i = 0; i < numSerpientes; i++) {
            do {
                interfaz.mostrarEnunciado("Agregar serpiente("+i+")");
                serpientes[i][0] = interfaz.preguntarPosicion("Insertar la posicion X Inicial de la serpiente("+i+")");
                serpientes[i][1] = interfaz.preguntarPosicion("Insertar la posicion Y Inicial de la serpiente("+i+")");
                serpientes[i][2] = interfaz.preguntarPosicion("Insertar la posicion X Final de la serpiente("+i+")");
                serpientes[i][3] = interfaz.preguntarPosicion("Insertar la posicion Y Final de la serpiente("+i+")");
                if(!existeSerpiente(serpientes[i][0], serpientes[i][1], serpientes, i)) {
                    if(validarDatosSerpiente(serpientes[i][0], serpientes[i][1], serpientes[i][2], serpientes[i][3], tablero)){
                        validado = true;
                    } else {
                        validado = false;
                        interfaz.mostrarError("Los datos de la serpiente no pueden ser posibles.");
                    }
                } else {
                    validado = false;
                    interfaz.mostrarError("Ya existe una serpiente en esa posicion.");
                }
            } while(!validado);
        }
        for (int i = 0; i < numSerpientes; i++) {
            setPosicion(serpientes[i][0],serpientes[i][1],serpientes[i][2],serpientes[i][3]);
            if(i != numSerpientes-1) {
                setSeparador();
            }
        }
        super.setLinea();
    }
    
    /**
     * Pide los datos de las escaleras por consola validados
     */
    private void pedirDatosEscaleras() {
        boolean validado;
        interfaz.mostrarFase("Escaleras");
        numEscaleras = interfaz.preguntarNumero("¿Cuántas escaleras deseas agregar?");
        escaleras = new int[numEscaleras][4];
        for (int i = 0; i < numEscaleras; i++) {
            do {
                interfaz.mostrarEnunciado("Agregar escalera("+i+")");
                escaleras[i][0] = interfaz.preguntarPosicion("Insertar la posicion X Inicial de la escaleras("+i+")");
                escaleras[i][1] = interfaz.preguntarPosicion("Insertar la posicion Y Inicial de la escaleras("+i+")");
                escaleras[i][2] = interfaz.preguntarPosicion("Insertar la posicion X Final de la escaleras("+i+")");
                escaleras[i][3] = interfaz.preguntarPosicion("Insertar la posicion Y Final de la escaleras("+i+")");
                if(!existeSerpiente(escaleras[i][0], escaleras[i][1], serpientes, serpientes.length)) {
                    if(!existeEscalera(escaleras[i][0], escaleras[i][1], escaleras, i)) {
                        if(validarDatosEscalera(escaleras[i][0], escaleras[i][1], escaleras[i][2], escaleras[i][3], tablero)){
                            validado = true;
                        } else {
                            validado = false;
                            interfaz.mostrarError("Los datos de la escalera no pueden ser posibles.");
                        }
                    } else {
                        validado = false;
                        interfaz.mostrarError("Ya existe una escalera en esa posicion.");
                    }
                } else {
                    validado = false;
                    interfaz.mostrarError("Ya existe una serpiente en esa posicion.");
                }
            } while(!validado);
        }
        for (int i = 0; i < numEscaleras; i++) {
            setPosicion(escaleras[i][0],escaleras[i][1],escaleras[i][2],escaleras[i][3]);
            if(i != numSerpientes-1) {
                setSeparador();
            }
        }
    }
    
    /**
     * Funcion publica que crea los datos desde la consola
     */
    public void crearDatos() {
        pedirDatosTablero();
        pedirDatosSerpientes();
        pedirDatosEscaleras();
        close();
    }
    
    /**
     * Crea los datos predeterminados definidos en esta funcion
     */
    public void crearDatosPredeterminados() {
        setPosicion(7,0,0,0); //Tablero
        setLinea();
        setPosicion(0,1,3,2); //Serpiente 1
        setSeparador();
        setPosicion(3,4,5,2);  //Serpiente 2
        setSeparador();
        setPosicion(6,7,7,1);  //Serpiente 3
        setLinea();
        setPosicion(3,0,0,7);  // Escalera 1
        setSeparador();
        setPosicion(5,4,3,1); // Escalera 2
        setSeparador();
        setPosicion(1,5,2,3); // Escalera 3
        close();
    }
}
