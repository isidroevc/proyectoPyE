package estadisticadescriptiva.revision;

/*Funcion o preceso para calcular la media o promedio como lo quieran decir*/

public  class Funcion{
private double datos [];
private double  media;
private double sumaDatos;


public Funcion (double datos []){
this.datos = datos;

/* calcular el promedio*/

    sumaDatos = 0;
for (int i = 0; i < datos.length ; i++) {
    sumaDatos = datos[i];

}

media = sumaDatos / datos.length;


}



}
