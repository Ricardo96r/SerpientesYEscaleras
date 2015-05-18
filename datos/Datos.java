/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.datos;

import java.io.File;

/**
 *
 * @author Ricardo
 */
public class Datos {
    protected String archivo = "datos.txt";
    
    /**
     * Confirma si el archivo existe.
     * 
     * @return boolean
     */
    public boolean existeArchivo() {
        File archivo = new File(this.archivo);
        return archivo.exists();
    }

    public String getArchivo() {
        return archivo;
    }
    
}
