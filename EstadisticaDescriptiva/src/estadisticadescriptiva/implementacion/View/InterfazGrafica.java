/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.implementacion.View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 *
 * @author Zaira Cortés Jiménez
 */
public class InterfazGrafica extends JFrame{
    JFrame ventana;
    JPanel panel;
    JTextField cArchivo;
    JTextField cSeparador;
    JButton examinar;
    JButton correr;
    JComboBox separadores;
    JRadioButton separador;
    JRadioButton otrossep;
    JLabel fondo;
    JLabel archivo;
    
    public static void main(String[] args) {
        InterfazGrafica n = new InterfazGrafica();

        n.mostrar();
    }
    
    void mostrar(){
        crear();
        ensamblar();
    }
    
    void crear() {
        ventana = new JFrame();
        panel = new JPanel();
        cArchivo = new JTextField();
        cSeparador = new JTextField();
        examinar = new JButton("Examinar");
        correr = new JButton("Correr");
        separador = new JRadioButton("Separador");
        otrossep = new JRadioButton("Otro Seprador:");
        separadores = new JComboBox();
        fondo = new JLabel();
        archivo = new JLabel("Archivo");
        
    }
    
    void ensamblar (){
        ventana.setTitle("Estadistica Descriptiva");
        ventana.setSize(600, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.getContentPane().add(panel);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setIconImage(new ImageIcon("src/estadisticadescriptiva/implementacion/View/Escudo-ITL.jpg").getImage());
        
        panel.setLayout(null);
        
        panel.add(archivo);
        panel.add(examinar);
        panel.add(cArchivo);
        panel.add(separador);
        panel.add(separadores);
        panel.add(otrossep);
        panel.add(cSeparador);
        panel.add(correr);   
        panel.add(fondo);
        
        fondo.setIcon(new ImageIcon("src/estadisticadescriptiva/implementacion/View/banner2016.png"));
        fondo.setBounds(0, 0, 600, 73);
        fondo.setFocusable(false);
        
        archivo.setBounds(20, 83, 120, 30);
        
        examinar.setBounds(430, 123, 100, 30);
        
        cArchivo.setBounds(20, 123, 400, 30);
        
        separador.setBounds(20, 173, 100, 30);
        
        separadores.setBounds(20, 213, 200, 30);
        
        otrossep.setBounds(20, 300, 200, 30);
        
        cSeparador.setBounds(230, 300, 300, 30);
        
        correr.setBounds(430 ,400, 100, 30);
        
    }
    
}
