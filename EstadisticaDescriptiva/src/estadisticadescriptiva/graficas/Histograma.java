/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.graficas;

import estadisticadescriptiva.datos.Clase;
import estadisticadescriptiva.datos.DatosAgrupados;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

/**
 *
 * @author isidro
 */
public class Histograma extends Grafica {

    // -Atributos.
    private DatosAgrupados origen;
    private Clase[] clases;
    private boolean forzar; // - Indica si deben forzarse las dimensiones dadas
    public final static float MARGEN_X = 0.1f, 
            MARGEN_Y = 0.15f,// 10% demargen
            GROSOR_ESTANDAR = 2.5f; 
    public final int ANCHO_ESTANDAR = 480, 
            ALTO_ESTANDAR = 640,
            MIN_ANCHO_BARRA = 2;

    // -Constructor
    public Histograma(int altura, int anchura, Color fondo,
            DatosAgrupados origen, boolean forzar) {
        super(altura, anchura, fondo);
        this.origen = origen;
        clases = origen.getClases();

    }

    // -Métodos de acceso.
    public DatosAgrupados getOrigen() {
        return this.origen;
    }

    public void setOrigen(DatosAgrupados origen) {
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
            margenYinf,
            margenYsup,
            nClases,
            anchoBarra,
            longitudEjeX,
            longitudGuia,
            longitudEjeY,
            frecuenciaMaxima = Integer.MIN_VALUE,
            alturaBarra,
            escala,cifraEscala,
            anchoCaracter,
            longitudDivY,
            espacioAnterior,
            i;
        double coefPF;
        Graphics2D pluma;
        String cifraEscalaStr;
        //NOTA: TODAS LAS DIMENSIONES SON ENTEROS YA QUE NO SE PUEDEN DIVIDIR
        //PIXELES
        for (Clase c : clases) {
            if (frecuenciaMaxima < c.getFrecuenciaA()) {
                frecuenciaMaxima = c.getFrecuenciaA();
            }
        }
        nClases = clases.length;

        margenX = (int) (anchura * MARGEN_X);
        margenYinf = (int) (altura * MARGEN_Y);
        margenYsup = (int)(altura * 0.5 * MARGEN_Y);
        longitudEjeX = anchura - 2 * margenX + 10;
        longitudEjeY = (altura - margenYsup - margenYinf);
        anchoBarra = (int) (longitudEjeX / nClases);
        coefPF = (double) longitudEjeY / frecuenciaMaxima;
        if (!forzar && anchoBarra < MIN_ANCHO_BARRA) {
            anchura = MIN_ANCHO_BARRA * nClases + margenX;
            anchoBarra = MIN_ANCHO_BARRA;
            longitudEjeX = anchura - 2 * margenX + 10;
        }
        grafica = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_ARGB);
        pluma = (Graphics2D)grafica.getGraphics();
        pluma.setColor(fondo);
        
        pluma.setStroke(new BasicStroke(GROSOR_ESTANDAR * (anchura/ANCHO_ESTANDAR)));
        espacioAnterior = (int)(2 * GROSOR_ESTANDAR * (anchura/ANCHO_ESTANDAR));
        
        //Pintar fondo
        pluma.fillRect(0, 0, anchura, altura);
        pluma.setColor(Color.BLACK);
        //Trazar los ejes.
        //Eje X
        pluma.drawLine(margenX, altura - margenYinf, anchura - margenX, altura - margenYinf);
        // Eje Y
        pluma.drawLine(margenX, altura - margenYinf, margenX, margenYsup);

        //Trazar cada una de las barras.
        i = margenX + espacioAnterior;
        //pluma.setFont(new Font("Times New Roman", 10, 0));
        
        for (Clase c : clases) {
            alturaBarra = (int) (coefPF * (double) c.getFrecuenciaA());
            System.out.println("longitudEjeY: " + longitudEjeY);
            pluma.drawRect(i, margenYsup + (longitudEjeY - alturaBarra), anchoBarra, alturaBarra);
            i += anchoBarra;
        }
        //Trazar la escala en y.
        System.out.println(frecuenciaMaxima);
        escala = longitudEjeY/12;
        longitudDivY = 5 * (anchura/ANCHO_ESTANDAR);
        
        pluma.setFont(new  Font(pluma.getFont().getName(),0, escala/3));
        anchoCaracter = pluma.getFontMetrics().getWidths()[125];
        //System.out.println(pluma);
        i = margenYsup;
        for(int j = 0; j <= 12; j++){
            pluma.drawLine(margenX - longitudDivY, i, margenX, i);
            cifraEscala =  (int)((12 -j) * (escala/coefPF));
            cifraEscalaStr = Integer.toString(cifraEscala);
            pluma.drawString(cifraEscalaStr,margenX - cifraEscalaStr.length()* anchoCaracter -longitudDivY,i-3);
            i+=escala;
            
        }
        //trazar escala en X
        longitudGuia = anchoBarra * clases.length + margenX +espacioAnterior;
        System.out.println("guia: " + longitudGuia);
        pluma.drawLine(margenX + espacioAnterior, 
                altura - (margenYinf - espacioAnterior), 
                longitudGuia, 
                altura - (margenYinf - espacioAnterior));
        
        
        //
        escala = (int)(longitudGuia/4);
        i = margenX + espacioAnterior;
        for(int j= 0; j <= 4; j++){
            pluma.drawLine(i,altura - margenYinf, i, altura - margenYinf + longitudDivY +espacioAnterior);
            i+=escala;
        }
    }

}
