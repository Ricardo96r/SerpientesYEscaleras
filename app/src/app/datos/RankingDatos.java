/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.datos;

import app.consola.InterfazConsola;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Ricardo
 */
public class RankingDatos extends LeerDatos {
    private String jugadores[][];
    private String archivo = "ranking.txt";
    private FileWriter fWDatos;
    private PrintWriter escribirDatos;
    private InterfazConsola interfaz = new InterfazConsola();
    
    /**
     * Constructor para la clase Datos
     */
    public RankingDatos() {
        super("ranking.txt");
        if(!existeArchivo()) {
            try {
                File file = new File(archivo);
                file.createNewFile();
            } catch (IOException ex) {
                interfaz.mostrarError("Error al leer o escribir el raking.txt");
            }
        }
    }
    
    
    /**
     * Agrega el nombre ganador al archivo de guardado y le suma una victoria
     * 
     * @param nombre Nombre del jugador
     */
    public void agregarGanador(String nombre) {
        int sumar;
        try {
            if(existeArchivo()) {
                if(existeJugador(nombre)) { 
                    for (int i = 0; i < jugadores.length; i++) {
                        if (jugadores[i][0].equals(nombre)) {
                        sumar = Integer.parseInt(jugadores[i][1]);
                        sumar++;
                        jugadores[i][1] = String.valueOf(sumar);
                        }
                    }
                    crearDatos();
                    close();
                } else {
                    crearDatos();
                    setDatosJugador(nombre,"1");
                    close();
                }
            } else {
                jugadores = new String[1][2];
                jugadores[0][0] = nombre;
                jugadores[0][1] = "1";
                crearDatos();
                close();
            }
        } catch(Exception e) {
            interfaz.mostrarError("Datos corruptos de ranking. Se ha borrado todo y creado denuevo");
            jugadores = new String[1][2];
            jugadores[0][0] = nombre;
            jugadores[0][1] = "1";
            crearDatos();
            close();
        }
    }
    
    /**
     * Devuelve un String de los tres mejores guardados del archivo de texto
     * @return String los tres mejores jugadores
     */
    public String[][] tresMejores() {
        int mayor = 0;
        int limite = 0;
        String mejores[][] = new String[3][2];
        String guardar[];
        separarDatos();
        for (int i = 0; i < jugadores.length; i++) {
            mayor = i;
            for (int j = i; j < jugadores.length; j++) {
                if(Integer.parseInt(jugadores[mayor][1]) < Integer.parseInt(jugadores[j][1])) {
                    mayor = j;
                }
            }
            guardar = jugadores[i];
            jugadores[i] = jugadores[mayor];
            jugadores[mayor] = guardar;
        }
        if(jugadores.length < 3) {
            limite = jugadores.length;
        } else {
            limite = 3;
        }
        for (int i = 0; i < limite; i++) {
            mejores[i] = jugadores[i];
        }
        return mejores;
    }
    
    /**
     * Metodo clave para escribir datos en un archivo de texto
     */
    private void escribirDatos() {
        try {
            fWDatos = new FileWriter(archivo);
        } catch (IOException ex) {
            System.out.println("-ERROR: al escribir archivo: "+ex.getMessage());
        }
        escribirDatos = new PrintWriter(fWDatos);
    }
    
    /**
     * Crea los datos a partir de los datos guardados en el atributo jugadores
     */
    private void crearDatos() {
        escribirDatos();
        for (int i = 0; i < jugadores.length; i++) {
            setDatosJugador(jugadores[i][0], jugadores[i][1]);
            setLinea();
        }
    }

    /**
     * Si existe un jugador (nombre) guardado en el archivo de texto
     * 
     * @param nombre
     * @return boolean
     */
    private boolean existeJugador(String nombre) {
        boolean existe = false;
        separarDatos();
        for (int i = 0; i < jugadores.length; i++) {
            if(jugadores[i][0].equals(nombre)) {
                existe = true;
            }
        }
        return existe;
    }
    
    /**
     * Separa los datos por lineas y ","
     */
    private void separarDatos() {
        String linea = obtenerLinea(1);
        jugadores = new String[datosLineas.length][];
        for (int i = 0; i < jugadores.length; i++) {
            linea = obtenerLinea(i+1);
            this.jugadores[i] = linea.split(":");
        }
    }
    
    /**
     * Metodo esencial para poder cerrar el archivo de texto y que se guarden 
     * los datos
     */
    private void close() {
        escribirDatos.close();
    }
    
    /**
     * Agrega al archivo el nombre y las victorias
     * @param nombre Nombre del jugador
     * @param ganadas Victorias ganadas
     */
    private void setDatosJugador(String nombre, String ganadas) {
        escribirDatos.append(nombre+":"+ganadas);
    }
    
    /**
     * Agrega una linea al archivo de texto
     */
    private void setLinea() {
       escribirDatos.append("\r\n");
    }
}
