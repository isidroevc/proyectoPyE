/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.graficas;

import estadisticadescriptiva.datos.Clase;
import estadisticadescriptiva.datos.DatosAgrupados;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
    public final static float MARGEN_X = 0.1f, MARGEN_Y = 0.15f; // 10% demargen
    public final int ANCHO_ESTANDAR = 480, ALTO_ESTANDAR = 640,MIN_ANCHO_BARRA = 10;

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
            longitudEjeY,
            frecuenciaMaxima = Integer.MIN_VALUE,
            alturaBarra,
            escalaX,escalaY,cifraEscala,
            anchoCaracter,
            longitudDivY,
            i;
        double coefPF;
        Graphics pluma;
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
        pluma = grafica.getGraphics();
        pluma.setColor(fondo);

        //Pintar fondo
        pluma.fillRect(0, 0, anchura, altura);
        pluma.setColor(Color.BLACK);
        //Trazar los ejes.
        //Eje X
        pluma.drawLine(margenX, altura - margenYinf, anchura - margenX, altura - margenYinf);
        // Eje Y
        pluma.drawLine(margenX, altura - margenYinf, margenX, margenYsup);

        //Trazar cada una de las barras.
        i = margenX + 10;
        //pluma.setFont(new Font("Times New Roman", 10, 0));
        
        for (Clase c : clases) {
            alturaBarra = (int) (coefPF * (double) c.getFrecuenciaA());
            System.out.println("longitudEjeY: " + longitudEjeY);
            pluma.drawRect(i, margenYsup + (longitudEjeY - alturaBarra), anchoBarra, alturaBarra);
            i += anchoBarra;
        }
        //Trazar la escala en y.
        System.out.println(frecuenciaMaxima);
        escalaY = longitudEjeY/12;
        longitudDivY = 5 * (anchura/ANCHO_ESTANDAR);
        
        pluma.setFont(new  Font(pluma.getFont().getName(),0, escalaY/3));
        anchoCaracter = pluma.getFontMetrics().getWidths()[125];
        System.out.println(anchoCaracter);
        i = margenYsup;
        for(int j = 0; j < 12; j++){
            pluma.drawLine(margenX - longitudDivY, i, margenX, i);
            cifraEscala =  (int)((12-j) * (escalaY/coefPF));
            cifraEscalaStr = Integer.toString(cifraEscala);
            pluma.drawString(cifraEscalaStr,margenX - cifraEscalaStr.length() - 2* anchoCaracter,i-3);
            i+=escalaY;
            
        }
        
    }

}
