/**
 * 
 */
package estadisticadescriptiva.graficas;

import estadisticadescriptiva.datos.DatosAgrupados;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Vásquez Cortés Isidro Emmanuel
 */
public abstract class Grafica {
    // -Atributos 
    protected BufferedImage grafica; // -Es la imagen de la gráfica en sí.
    protected Color fondo;
    protected boolean dibujado;
    protected int altura; 
    protected int anchura;
    // -Constructotes
    
    public Grafica(int anchura, int altura, Color fondo){
        this.altura = altura;
        this.anchura = anchura;
    }
    
    public abstract void dibujar();
    
    /**
     * Retorna la instancia de BufferedImage que contiene la grafica
     * @return 
     */
    public BufferedImage getGrafica(){
        return this.grafica;
    }
    
    public void guardarEnDisco(String ruta, String formato) throws IOException{
        ImageIO.write(grafica, formato, new File(ruta));
    }
    
    public enum Formatos{
        JPEG,
        PNG,
        GIF
    }
}
