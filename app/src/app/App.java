/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app;

import app.consola.Consola;

/**
 *
 * @author Ricardo
 */
public class App {

    /**
     * Metodo main de la aplicacion. Se inicia el juego por consola
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Consola consola = new Consola();
        consola.iniciarJuego();
    }
}
