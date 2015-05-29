/*
 * 
 * Juego de serpientes y escaleras
 *
 * Creado por Ricardo Rodriguez <ricardo96r@gmail.com>
 */
package app.datos;

/**
 *
 * @author Ricardo
 */
public abstract class Datos {
    protected String archivo;
    
    public Datos(String archivo) {
        this.archivo = archivo;
    }
    
    /**
     * Confirma si el archivo existe.
     * 
     * @return boolean
     */
    abstract public boolean existeArchivo();

    /**
     * Obtiene el nombre del archivo
     * @return 
     */
    public String getArchivo() {
        return archivo;
    }
    
}
