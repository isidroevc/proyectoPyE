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
import java.text.DecimalFormat;

/**
 *
 * @author isidro
 */
public class Histograma extends Grafica {

    // -Atributos.
    private DatosAgrupados origen;
    private Clase[] clases;
    public final static float        MARGEN_X = 0.1f;
    public final static float        MARGEN_Y = 0.15f;// 10% demargen
    public final static float        GROSOR_ESTANDAR = 2.5f; 
    public final static int          ANCHO_ESTANDAR = 640;
    public final static int          ALTO_ESTANDAR = 480;
    public final static int          MIN_ANCHO_BARRA = 2;

    // -Constructor
    public Histograma(int altura, int anchura, Color fondo,
            DatosAgrupados origen) {
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
            escala,
            noEscalas,
            longitudDivY,
            espacioAnterior,
            i;
        double coefPF,cifraEscala,amplitudEscala, datoMinimo, rango;
        Graphics2D pluma;
        DecimalFormat formato = new DecimalFormat("########.####");
        String cifraEscalaStr, leyendas;
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
        longitudEjeX = anchura - 2 * margenX;
        longitudEjeY = (altura - margenYsup - margenYinf);
        anchoBarra = (int) (longitudEjeX / nClases);
        coefPF = (double) longitudEjeY / frecuenciaMaxima;
        grafica = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB);
        pluma = (Graphics2D)grafica.getGraphics();
        pluma.setColor(fondo);
        
        pluma.setStroke(new BasicStroke(GROSOR_ESTANDAR * (anchura/ANCHO_ESTANDAR)));
        espacioAnterior = (int)(2 * GROSOR_ESTANDAR * (anchura/ANCHO_ESTANDAR));
        
        //Pintar fondo
        pluma.fillRect(0, 0, anchura, altura);
        pluma.setColor(Color.BLACK);
        //Trazar los ejes.
        //Eje X
        pluma.drawLine(margenX, altura - margenYinf, longitudEjeX + margenX, altura - margenYinf);
        // Eje Y
        pluma.drawLine(margenX, altura - margenYinf, margenX, margenYsup);

        //Trazar cada una de las barras.
        i = margenX + espacioAnterior;
        //pluma.setFont(new Font("Times New Roman", 10, 0));
        
        for (Clase c : clases) {
            alturaBarra = (int) (coefPF * (double) c.getFrecuenciaA());
            pluma.drawRect(i, margenYsup + (longitudEjeY - alturaBarra), anchoBarra, alturaBarra);
            i += anchoBarra;
        }
        //Trazar la escala en y.

        escala = longitudEjeY/12;
        longitudDivY = (int)(5 * (double)(anchura/ANCHO_ESTANDAR));
        
        pluma.setFont(new  Font(pluma.getFont().getName(),0, escala/3));
        i = margenYsup;
        for(int j = 0; j <= 12; j++){
            pluma.drawLine(margenX - longitudDivY, i, margenX, i);
            cifraEscala =  (int)((12 -j) * (escala/coefPF));
            cifraEscalaStr = Integer.toString((int)cifraEscala);
            pluma.drawString(cifraEscalaStr,
                    margenX - pluma.getFontMetrics().stringWidth(cifraEscalaStr) -longitudDivY,
                    i-3);
            i+=escala;
            
        }
        noEscalas = -1;
        for(i = 3; i <= 15; i++){
            if(clases.length % i == 0){
                noEscalas = i;
            }
        }
        if(noEscalas == -1){
            noEscalas = 4;
        }
        //trazar escala en X
        
        longitudGuia = clases.length * anchoBarra;
        escala = longitudGuia/noEscalas;
        pluma.drawLine(margenX + espacioAnterior, 
                altura - (margenYinf - espacioAnterior), 
                longitudGuia + margenX + espacioAnterior, 
                altura - (margenYinf - espacioAnterior));
        
        rango = (clases[clases.length - 1].getLimiteSuperior()  - clases[0].getLimiteInferior());
        
        amplitudEscala = rango/noEscalas;
        i = margenX + espacioAnterior;
        datoMinimo = clases[0].getLimiteInferior();
        for(int j= 0; j <= noEscalas; j++){
            cifraEscala = amplitudEscala * j + datoMinimo;
            cifraEscalaStr = formato.format(cifraEscala);
            pluma.drawLine(i,
                    altura-(margenYinf - espacioAnterior),
                    i,
                    altura - (margenYinf - espacioAnterior - longitudDivY));
            pluma.drawString(cifraEscalaStr,
                    i-(pluma.getFontMetrics().stringWidth(cifraEscalaStr))/2,
                    altura - (margenYinf - espacioAnterior - (j%2)* (longitudDivY+3) - 3*longitudDivY));
            i += escala;
        }
        //Colocar leyenda "Histograma"
        leyendas = "Histograma";
        pluma.drawString(leyendas, (anchura - (pluma.getFontMetrics().stringWidth(leyendas)))/2,
                espacioAnterior*2);
        leyendas = "Unidades";
        pluma.drawString(leyendas, (anchura - (pluma.getFontMetrics().stringWidth(leyendas)))/2,
                altura - 4*espacioAnterior);
    }

}
