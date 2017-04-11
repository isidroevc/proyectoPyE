package estadisticadescriptiva.revision;

import java.util.Random;

/*Funcion o preceso para calcular la media o promedio como lo quieran decir*/
public class Funcion {

    
    
    public static void main(String[] args ){
        //Se crean datos aleatorios de prueba.
        double[] datos = new double[12];
        Random aleatorios = new Random();
        System.out.println("Creando datos de prueba aleatorios");
        for(int i = 0, count = datos.length; i < count; i++){
            datos[i] = Math.abs(aleatorios.nextInt()) % 25;
            System.out.println(datos[i]);
        }
        System.out.println("Media aritmetica de los datos: " + CalcularMedia(datos));
    }
    public static double CalcularMedia(double datos[]) {

        /* calcular el promedio*/
        double sumaDatos = 0;
        for (int i = 0; i < datos.length; i++) {
            sumaDatos = datos[i];

        }

        double media = sumaDatos / datos.length;

        return media;
    }

}
