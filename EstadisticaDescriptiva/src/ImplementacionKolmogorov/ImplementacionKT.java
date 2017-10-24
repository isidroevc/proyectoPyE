/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImplementacionKolmogorov;

import Distribuciones.DistribucionUniforme;
import Distribuciones.PruebaKolmogorovSmirnov;
import estadisticadescriptiva.datos.ArchivoDeDatos;
import estadisticadescriptiva.datos.DatosEnBruto;

/**
 *
 * @author Alison
 */
public class ImplementacionKT {
    static String[] motores = {"bm-", "bmx-", "dm-", "dmx-", "python-", "cpp", "java-", "R-", "VB"};
    static String[] nombres = {"BM", "BMX", "DM", "DMX", "python", "cpp", "java", "R", "VB"};
    static int[] inicios = {1,1,1,1,1,0,0,0,0};
    static String[] separadores = {",", ",", ",", ",", ",", ",", ",", "\n", ","};
    static int[] aceptados = new int[9];
    static int[] rechazados = new int[9];
    public static void main(String[] args) {
        String mensaje = "";
        int f = 0;
        ImplementacionKT kt = new ImplementacionKT();
        
        for(int i = 0; i < 9; i++){
            mensaje += "\n" + kt.analizarArchivos(nombres[i], motores[i],
                    inicios[i], 100 + inicios[i], i, separadores[i]);
        }
        System.out.println(mensaje);
    }
    
    public String analizarArchivos(String carpeta,String nombre,
                                   int inicio, int fin, int indice,
                                   String separador){
        ArchivoDeDatos arch;
        DatosEnBruto datos;
        DistribucionUniforme du;
        PruebaKolmogorovSmirnov ks;
        String mensaje = "Motor " + nombre;
        String alt;
        if(nombre.equals("VB"))
            alt = "";
        else 
            alt = nombre;
        for(int i = inicio; i < fin; i++){
            arch = new ArchivoDeDatos("archivos/" + carpeta + "/" + alt + i + ".txt", separador);
            try{
                arch.leerDatos();
                datos = new DatosEnBruto(arch.getDatos(true));
                du = new DistribucionUniforme(datos.getN(), datos.getMenor(), datos.getMayor());
                ks = new PruebaKolmogorovSmirnov(datos.getDatos(), du, 0.05, true);
                ks.analizar();
                if(ks.cumple()) {
                    aceptados[indice]++;
                } else {
                    rechazados[indice]++;
                }
            }catch(Exception ex) {
                System.out.println(ex);
            }
        }
        mensaje += "\nAceptados:" + aceptados[indice];
        mensaje += "\nRechazados: " + rechazados[indice] +"\n";
        return mensaje;
    }
}
