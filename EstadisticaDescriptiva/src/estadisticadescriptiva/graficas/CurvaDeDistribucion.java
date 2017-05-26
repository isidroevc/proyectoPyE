/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.graficas;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionNormal;
import estadisticadescriptiva.datos.Clase;
import static estadisticadescriptiva.graficas.Histograma.ANCHO_ESTANDAR;
import static estadisticadescriptiva.graficas.Histograma.GROSOR_ESTANDAR;
import static estadisticadescriptiva.graficas.Histograma.MARGEN_X;
import static estadisticadescriptiva.graficas.Histograma.MARGEN_Y;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class CurvaDeDistribucion extends Grafica {

    public final static float MARGEN_X = 0.1f;
    public final static float MARGEN_Y = 0.15f;// 15% demargen
    public final static float GROSOR_ESTANDAR = 2.5f;
    public final static int ANCHO_ESTANDAR = 640;
    public final static int ALTO_ESTANDAR = 480;
    public final static int MIN_ANCHO_BARRA = 2;

    private Distribucion dist, normalAprox;
    private double min, max;

    public CurvaDeDistribucion(int anchura, int altura, Distribucion dist, double min, double max, Color fondo) {
        super(anchura, altura, fondo);
        this.dist = dist;
        this.min = min;
        this.max = max;
        if (!(dist.getClass() == DistribucionNormal.class)) {
            normalAprox = new DistribucionNormal(dist.calcularMedia(), dist.calcularDesviacionE());
        } else {
            normalAprox = dist;
        }
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
                alturaBarra,
                escala,
                noEscalas,
                longitudDivY,
                espacioAnterior,
                fuente,
                i,
                x1,
                x2,
                y1,
                y2,
                origenX;
        double coefPF, proporcionX,
                cifraEscala,
                amplitudEscala,
                datoMinimo,
                rango = max - min,
                probabilidadMaxima = obtenerProbabilidadMaxima(dist, normalAprox, min, max);
        normalAprox = dist;
        System.out.println("max prob: " + probabilidadMaxima);
        if (dist.getTipo() != Distribucion.Tipos.Continua) {
            normalAprox = new DistribucionNormal(dist.calcularMedia(), dist.calcularDesviacionE());
        }
        Graphics2D pluma;
        DecimalFormat formato = new DecimalFormat("#.####");
        String cifraEscalaStr, leyendas;
        margenX = (int) (anchura * MARGEN_X);
        margenYinf = (int) (altura * MARGEN_Y);
        margenYsup = (int) (altura * 0.5 * MARGEN_Y);
        longitudEjeX = anchura - 2 * margenX;
        longitudEjeY = (altura - margenYsup - margenYinf);
        coefPF = (double) longitudEjeY / probabilidadMaxima;
        grafica = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB);
        pluma = (Graphics2D) grafica.getGraphics();
        pluma.setColor(fondo);

        pluma.setStroke(new BasicStroke(GROSOR_ESTANDAR * (anchura / ANCHO_ESTANDAR)));
        espacioAnterior = (int) (2 * GROSOR_ESTANDAR * (anchura / ANCHO_ESTANDAR));

        //Pintar fondo
        pluma.fillRect(0, 0, anchura, altura);
        pluma.setColor(Color.BLACK);
        //Trazar los ejes.
        //Eje X
        pluma.drawLine(margenX, altura - margenYinf, longitudEjeX + margenX, altura - margenYinf);
        // Eje Y
        pluma.drawLine(margenX, altura - margenYinf, margenX, margenYsup);

        //Trazar la escala en y.
        noEscalas = 8;
        escala = longitudEjeY / noEscalas;
        longitudDivY = (int) (5 * (double) (anchura / ANCHO_ESTANDAR));
        //fuente = espacioAnterior * 4;
        fuente = (int) (longitudDivY * 2);
        pluma.setFont(new Font(pluma.getFont().getName(), 0, fuente));
        i = margenYsup;
        for (int j = 0; j <= noEscalas; j++) {
            pluma.drawLine(margenX - longitudDivY, i, margenX, i);
            cifraEscala = (double) (noEscalas - j) * (escala / coefPF);
            cifraEscalaStr = formato.format(cifraEscala);
            pluma.drawString(cifraEscalaStr,
                    margenX - pluma.getFontMetrics().stringWidth(cifraEscalaStr) - longitudDivY,
                    i - 3);
            i += escala;

        }
        noEscalas = 8;
        //trazar escala en X

        longitudGuia = longitudEjeX;
        escala = longitudGuia / noEscalas;
        pluma.drawLine(margenX + espacioAnterior,
                altura - (margenYinf - espacioAnterior),
                longitudGuia + margenX + espacioAnterior,
                altura - (margenYinf - espacioAnterior));

        amplitudEscala = rango / noEscalas;
        i = margenX + espacioAnterior;
        datoMinimo = min;
        for (int j = 0; j <= noEscalas; j++) {
            cifraEscala = amplitudEscala * j + datoMinimo;
            cifraEscalaStr = formato.format(cifraEscala);
            pluma.drawLine(i,
                    altura - (margenYinf - espacioAnterior),
                    i,
                    altura - (margenYinf - espacioAnterior - longitudDivY));
            pluma.drawString(cifraEscalaStr,
                    i - (pluma.getFontMetrics().stringWidth(cifraEscalaStr)) / 2,
                    altura - (margenYinf - espacioAnterior - (j % 2) * (longitudDivY + 3) - 3 * longitudDivY));
            i += escala;
        }

        //Colocar leyenda "Histograma"
        leyendas = "Histograma";
        pluma.drawString(leyendas, (anchura - (pluma.getFontMetrics().stringWidth(leyendas))) / 2,
                espacioAnterior * 2);
        leyendas = "Unidades";
        pluma.drawString(leyendas, (anchura - (pluma.getFontMetrics().stringWidth(leyendas))) / 2,
                altura - 4 * espacioAnterior);

        origenX = margenX + espacioAnterior;
        pluma.setColor(Color.RED);
        coefPF = (double) longitudEjeY / (probabilidadMaxima);
        proporcionX = longitudEjeX / (max - min);
        x1 = margenX + espacioAnterior;
        while (x1 < longitudEjeX + origenX) {
            //obtener coordenada Y del punto a graficar.
            y1 = margenYsup + longitudEjeY - (int) ((double) (normalAprox.probabilidad((x1 - origenX) / proporcionX + min) * coefPF));
            //System.out.println("'till then: " + n * normalAprox.probabilidad((x1-origenX)/proporcionX + min));
            x2 = x1 + 1;
            y2 = margenYsup + longitudEjeY - (int) ((double) (normalAprox.probabilidad((x2 - origenX) / proporcionX + min) * coefPF));
            //ahora sigue dibujar la linea.
            pluma.drawLine(x1, y1, x2, y2);
            //graficar un punto
            //pluma.fillOval(x - grosorLinea, y - grosorLinea, grosorLinea,(int)(grosorLinea*(propY/propX)));
            //ajustar la Y
            x1++;
        }
    }

    private double obtenerProbabilidadMaxima(Distribucion dist, Distribucion normalAprox, double min, double max) {

        double pMax = Double.MIN_VALUE,
                aux;
        if (!(dist.getClass() == DistribucionNormal.class)) {
            if (dist.getTipo() == Distribucion.Tipos.Continua) {
                for (double i = min; i < max; i += 0.001) {
                    aux = dist.probabilidad(i);
                    if (pMax < aux) {
                        pMax = aux;
                    }
                }
            } else {
                aux = normalAprox.probabilidad(normalAprox.calcularMedia());
                if (aux > pMax) {
                    pMax = aux;
                }
            }
        } else {
            pMax = dist.probabilidad(dist.calcularMedia());
        }
        return pMax;
    }

    private double obtenerProbabilidadMaxima() {
        double pMax = Double.MIN_VALUE,
                aux;
        if (!(dist.getClass() == DistribucionNormal.class)) {
            if (dist.getTipo() == Distribucion.Tipos.Continua) {
                for (double i = min; i < max; i += 0.0001) {
                    aux = dist.probabilidad(i);
                    if (pMax < aux) {
                        pMax = aux;
                    }
                }
            } else {
                aux = dist.probabilidad(dist.calcularMedia());
                if (aux > pMax) {
                    pMax = aux;
                }
            }
        } else {
            pMax = dist.probabilidad(dist.calcularMedia());
        }
        return pMax;
    }
}
