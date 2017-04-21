/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva;

import estadisticadescriptiva.Controller.Controlador;
import estadisticadescriptiva.View.InterfazGrafica;

/**
 *
 * @author Tharduz
 */
public class EstadisticaDescriptiva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controlador controlador = new Controlador(new InterfazGrafica());
        controlador.iniciar();
        
        
    }
    
}
