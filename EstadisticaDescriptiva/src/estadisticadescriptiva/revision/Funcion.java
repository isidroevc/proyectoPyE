package estadisticadescriptiva.revision;

/*Funcion o preceso para calcular la media o promedio como lo quieran decir*/

public  class Funcion{

public Double CalcularPromedio (double datos []){

/* calcular el promedio*/

   double sumaDatos = 0;
for (int i = 0; i < datos.length ; i++) {
    sumaDatos = datos[i];

}

double media = sumaDatos / datos.length;

return media;
}



}
