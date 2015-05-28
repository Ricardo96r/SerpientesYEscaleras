/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
