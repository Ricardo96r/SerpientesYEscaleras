/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.consola;

import app.datos.EscribirDatosConsola;
import app.datos.LeerDatos;
import app.datos.RankingDatos;
import app.tablero.TableroConsola;
import java.util.Random;

/**
 * 
 * Clase que maneja y procesa los datos a mostrar por la Consola 
 * (vista del usuario)
 * @author Ricardo
 */
public class Consola {
    private InterfazConsola pregunta = new InterfazConsola();
    private int tableroDatos[] = new int[4];
    private int serpientesDatos[][];
    private int numSerpientes;
    private int escalerasDatos[][];
    private String jugadoresNombres[];
    private int numEscaleras;
    private int numJugadores;
    private InterfazConsola interfaz = new InterfazConsola();
    private TableroConsola tablero;
    
    /**
     * Clase principal de la aplicacion
     */
    public void iniciarJuego() {
       menu();
    }
    
    /**
     * Clase que ordena los metodos de la aplicacion para obtener
     * los datos del archivo de texto
     */
    private void obtenerDatos() {
        LeerDatos datos = new LeerDatos();
        if(datos.existeArchivo()) {
            try {
                this.serpientesDatos = datos.getDatosSerpientes();
                this.numSerpientes = serpientesDatos.length;
                this.escalerasDatos = datos.getDatosEscalera();
                this.numEscaleras = escalerasDatos.length;
                this.tableroDatos = datos.getDatosTablero();
            } catch(Exception e) {
                interfaz.mostrarError("El archivo de datos esta corrupto tendras que crearlo denuevo");
                pedirDatos();
                obtenerDatos();
            }
        } else {
            interfaz.mostrarError("El archivo de guardado no existe");
            pedirDatos();
            obtenerDatos();
        }
    }
    
    /**
     * Metodo que pide el nombre de cada jugador y valida si este es igual
     */
    private void pedirJugadores() {
        jugadoresNombres = new String[numJugadores];
        boolean nombreValido = true;
        for (int i = 0; i < numJugadores; i++) {
            do {
                nombreValido = true;
                jugadoresNombres[i] = pregunta.preguntarCadena("Insertar el nombre del jugador("+(i+1)+")");
                for (int j = 0; j < i; j++) {
                    if(jugadoresNombres[i].equals(jugadoresNombres[j])) {
                        nombreValido = false;
                        interfaz.mostrarError("El nombre insertado ya existe en otro jugardo");
                    }
                }
            } while(!nombreValido);
            tablero.agregarJugador(jugadoresNombres[i], i);
        }
    }
    
    /**
     * Metodo que carga los datos obtenidos hacia el tablero
     */
    private void cargarDatos() {
        for (int i = 0; i < numSerpientes; i++) {
            tablero.agregarSerpiente(serpientesDatos[i][0], 
                                    serpientesDatos[i][1], 
                                    serpientesDatos[i][2], 
                                    serpientesDatos[i][3], i);    
        }
        for (int i = 0; i < numEscaleras; i++) {
            tablero.agregarEscalera(escalerasDatos[i][0], 
                                    escalerasDatos[i][1], 
                                    escalerasDatos[i][2], 
                                    escalerasDatos[i][3], i);
        }
    }
    
    /**
     * Metodo menu principal de la aplicacion
     */
    private void menu() {
        int opcion;
        interfaz.mostrarTitulo();
        interfaz.mostrarFase("Menu del juego");
        interfaz.mostrarOpcionMenu("Iniciar juego con los datos guardados anteriormente", 1);
        interfaz.mostrarOpcionMenu("Crear tablero del juego", 2);
        interfaz.mostrarOpcionMenu("Ver reglas del juego", 3);
        interfaz.mostrarOpcionMenu("Ver los 3 mejores jugadores", 4);
        System.out.println();
        opcion = interfaz.preguntarNumero("Insertar opcion");
        procesarMenu(opcion);
    }
    
    /**
     * Procesa los datos insertados por el usuario mediante el menu
     * @param opcion Dato insertado por el usuario
     */
    private void procesarMenu(int opcion) {
        switch(opcion) {
            case 1:
                iniciarJuegoConsola();
                break;
            case 2:
                pedirDatos();
                iniciarJuegoConsola();
                break;
            case 3:
                mostrarReglas();
                menu();
                break;
            case 4:
                mostrarRanking();
                menu();
                break;
            default:
                break;
        }
    }
    
    /**
     * Inicia la fase de juego de la aplicacion. 
     * Comenzado pidiendo a los jugadores para despues jugar
     */
    private void iniciarJuegoConsola() {
        obtenerDatos();
        interfaz.mostrarFase("Jugadores");
        numJugadores = pregunta.preguntarNumero("¿Cuantos jugadores tendra la partida?");
        tablero = new TableroConsola(numJugadores, numSerpientes, numEscaleras, tableroDatos);
        pedirJugadores();
        cargarDatos();
        jugar();
    }
    
    /**
     * Metodo que pregunta si quiere el usuario crear los datos del tablero
     */
    private void pedirDatos() {
        boolean respuesta;
        interfaz.mostrarTitulo();
        interfaz.mostrarFase("Datos");
        EscribirDatosConsola datosConsola = new EscribirDatosConsola();
        respuesta = pregunta.preguntarPorDosOpciones("¿Quieres crear los datos del juego tu mismo? Al escojer N se creara el predeterminado.");
        if(respuesta) {
            datosConsola.crearDatos();
        } else {
            datosConsola.crearDatosPredeterminados();
        }
        
    }
    
    /**
     * Metodo principal del juego, donde estan muchos objetos de la aplicacion
     * para obtener un resultado deseado.
     */
    private void jugar() {
        int contador = -1;
        int jugador = 0;
        do {
            contador++;
            if(contador == numJugadores) {
                contador = 0;
            }
            interfaz.mostrarTitulo();
            tablero.mostrarTableroPorConsola();
            System.out.println();
            jugador = contador + 1;
            if (pregunta.preguntarPorDosOpciones("Jugador("+jugador+"): Lanzar dado")) {
                Random dado = new Random();
                tablero.moverJugador(contador, dado.nextInt(6)+1);
            } else {
                tablero.moverJugador(contador, pregunta.preguntarNumero("¿Cuantas casillas quieres moverte?"));
            }
        } while(!(tablero.getJugadores()[contador].getPosicionX() == tableroDatos[2] && tablero.getJugadores()[contador].getPosicionY() == tableroDatos[3]));
        interfaz.mostrarTitulo();
        tablero.mostrarTableroPorConsola();
        System.out.println();
        interfaz.mostrarAviso("El ganador fue el jugador("+(contador+1)+") y su nombre es: "+ tablero.getJugadores()[contador].getNombre());
        RankingDatos ranking = new RankingDatos();
        ranking.agregarGanador(tablero.getJugadores()[contador].getNombre());
    }

    /**
     * Muestra las reglas
     */
    private void mostrarReglas() {
        do {
            interfaz.mostrarFase("Reglas");
            interfaz.mostrarRegla("Para ganar el juego, el jugador tendra que llegar a la casilla final");
            interfaz.mostrarRegla("Al caer en una casilla con serpiente el jugador bajara a una posicion determinada.");
            interfaz.mostrarRegla("Al llegar a una casilla con escalera el jugador subirá a una posicion determinada.");
            interfaz.mostrarRegla("La casilla final se identifica con "+interfaz.setColorFinalTablero("00"));
            interfaz.mostrarRegla("La casilla inicial se identifica con "+interfaz.setColorInicioTablero("00"));
            interfaz.mostrarRegla("La serpiente se identifica con "+interfaz.setColorSerpiente("$$"));
            interfaz.mostrarRegla("La escalera se identifica con: "+interfaz.setColorEscalera("##"));
            System.out.println();
        } while(!pregunta.preguntarPorDosOpciones("¿Ya has visto las reglas?"));
    }

    /**
     * Muestra los 3 mejores jugadores
     */
    private void mostrarRanking() {
        do {
            try {
                RankingDatos ranking = new RankingDatos();
                String mejores[][] = ranking.tresMejores();
                for (int i = 0; i < 3; i++) {
                    interfaz.mostrarOpcionMenu(mejores[i][0]+" : "+mejores[i][1], (i+1)); 
                }
                System.out.println();
            } catch(Exception e) {
                interfaz.mostrarError("Todavia no hay jugadores");
            }
        } while(!pregunta.preguntarPorDosOpciones("¿Ya has visto a los mejores jugadores?"));
    }
}
