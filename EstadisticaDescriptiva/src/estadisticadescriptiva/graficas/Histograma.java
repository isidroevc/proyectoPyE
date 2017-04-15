/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.graficas;

import estadisticadescriptiva.datos.Clase;
import estadisticadescriptiva.datos.DatosAgrupados;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author isidro
 */
public class Histograma extends Grafica{
    // -Atributos.
    private DatosAgrupados origen;
    private Clase[] clases;
    private boolean forzar; // - Indica si deben forzarse las dimensiones dadas
    public final static float MARGEN = 0.1f; // 10% demargen
    public final int MIN_ANCHO_BARRA = 50;
    // -Constructor
    public Histograma(int altura, int anchura, Color fondo, 
        DatosAgrupados origen, boolean forzar){
        super(altura, anchura, fondo);
        this.origen = origen;
        clases = origen.getClases();
        
        
    }
    // -Métodos de acceso.
    public DatosAgrupados getOrigen(){
        return this.origen;
    }
    public void setOrigen(DatosAgrupados origen){
        this.origen = origen;
    }
    public Clase[] getClases() {
        return clases;
    }

    public void setClases(Clase[] clases) {
        this.clases = clases;
    }
    
    @Override
    public void dibujar() {
        int margenX, 
                margenY, 
                nClases, 
                anchoBarra, 
                longitudEjeX, 
                longitudEjeY,
                frecuenciaMaxima = Integer.MIN_VALUE,
                alturaBarra,
                i;
        double coefPF;
        Graphics pluma;
        
        //NOTA: TODAS LAS DIMENSIONES SON ENTEROS YA QUE NO SE PUEDEN DIVIDIR
        //PIXELES
        for(Clase c : clases){
            if(frecuenciaMaxima < c.getFrecuenciaA()){
                frecuenciaMaxima = c.getFrecuenciaA();
            }
        }
        nClases = clases.length;
        
        margenX = (int)(anchura * MARGEN);
        margenY = (int)(altura  * MARGEN);
        longitudEjeX = anchura - 2 * margenX + 10;
        longitudEjeY = altura - 2 * margenY;
        anchoBarra = (int)(longitudEjeX / nClases);
        coefPF = longitudEjeY / frecuenciaMaxima;
        if(!forzar && anchoBarra < MIN_ANCHO_BARRA){
            anchura = anchura * MIN_ANCHO_BARRA * nClases + margenX;
            anchoBarra = MIN_ANCHO_BARRA;
            longitudEjeX = anchura - 2 * margenX + 10;
        }
        System.out.println("altura: " + altura + " anchura: " + anchura);
        grafica = new BufferedImage(anchura, altura,BufferedImage.TYPE_INT_ARGB);
        pluma = grafica.getGraphics();
        pluma.setColor(fondo);
        
        //Pintar fondo
        pluma.fillRect(0, 0, anchura, altura);
        pluma.setColor(Color.BLACK);
        //Trazar los ejes.
        //Eje X
        pluma.drawLine(margenX, altura - margenY, anchura - margenX, altura - margenY);
        // Eje Y
        pluma.drawLine(margenX, altura - margenY, margenX, margenY);
        
        //trazar cada una de las barras.
        i = margenX + 10;
        for(Clase c : clases){
            alturaBarra = (int)(coefPF * (double)c.getFrecuenciaA());
            pluma.drawRect(i, margenY + (longitudEjeY - alturaBarra) , anchoBarra, alturaBarra);
            i += anchoBarra;
        }
    }
    
}
