/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.graficas;

import Distribuciones.Distribucion;
import Distribuciones.DistribucionNormal;
import static estadisticadescriptiva.graficas.Histograma.ANCHO_ESTANDAR;
import static estadisticadescriptiva.graficas.Histograma.GROSOR_ESTANDAR;
import static estadisticadescriptiva.graficas.Histograma.MARGEN_X;
import static estadisticadescriptiva.graficas.Histograma.MARGEN_Y;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
        int origenX = (int) (anchura * MARGEN_X),
                origenY = (int) (altura * (1 - MARGEN_Y)),
                margenX = (int) (anchura * MARGEN_X),
                margenYinf = (int) (altura * MARGEN_Y),
                margenYsup = (int) (altura * 0.5 * MARGEN_Y),
                longitudEjeY = (altura - margenYsup - margenYinf),
                longitudEjeX = anchura - 2 * margenX,
                x1,
                y1,
                x2,
                y2;
        Graphics2D pluma;
        double proporcionX, proporcionY;
        //Inicializar todos la imagen
        grafica = new BufferedImage(anchura, altura, BufferedImage.TYPE_INT_RGB);
        pluma = (Graphics2D) grafica.getGraphics();
        pluma.setColor(fondo);

        //Pintar fondo.
        pluma.fillRect(0, 0, anchura, altura);

        pluma.setStroke(new BasicStroke(GROSOR_ESTANDAR * (anchura / ANCHO_ESTANDAR)));

        pluma.setColor(Color.BLACK);

        //Trazar los ejes.
        pluma.drawLine(origenX, origenY, longitudEjeX, origenY);
        //eje Y
        pluma.drawLine(origenX, origenY, origenX, margenYsup);

        //Graficar la curva.
        proporcionX = longitudEjeX / (double) (max - min);
        proporcionY = longitudEjeY / (double) obtenerProbabilidadMaxima();

        pluma.setStroke(new BasicStroke(1.6f));
        pluma.setColor(Color.RED);
        x1 = origenX;
        while (x1 < longitudEjeX) {
            //obtener coordenada Y del punto a graficar.
            y1 = (int) (normalAprox.probabilidad((double) x1 / proporcionX + min)*proporcionY) + margenYsup + (longitudEjeY - margenYinf);
            x2 = x1 + 1;
            y2 = (int) (normalAprox.probabilidad((double) x2 / proporcionY + min)*proporcionY) + margenYsup + (longitudEjeY - margenYinf);
            //ahora sigue dibujar la linea.
            pluma.drawLine(x1, y1, x2, y2);
            //graficar un punto
            //pluma.fillOval(x - grosorLinea, y - grosorLinea, grosorLinea,(int)(grosorLinea*(propY/propX)));
            //ajustar la Y
            x1++;
        }
    }

    private double obtenerProbabilidadMaxima() {
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
        }else{
            pMax = dist.probabilidad(dist.calcularMedia());
        }
        return pMax;
    }
}
