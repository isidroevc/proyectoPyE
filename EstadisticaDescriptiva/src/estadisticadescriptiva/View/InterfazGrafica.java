/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadisticadescriptiva.View;

import estadisticadescriptiva.Controller.Controlador;
import estadisticadescriptiva.EstadisticaDescriptiva;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.stage.FileChooser;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

/**
 *
 * @author Zaira Cortés Jiménez
 */
public class InterfazGrafica extends JFrame {

    private JPanel panel;
    private JTextField cArchivo;
    private JTextField cSeparador;
    private JButton examinar;
    private JButton correr;
    private JComboBox separadores;
    private JRadioButton separador;
    private JRadioButton otrossep;
    private JLabel fondo;
    private JLabel archivo;
    private ButtonGroup separadorGroup;

    private Controlador controlador;
    private FileChooser dialogoArchivo;

    public InterfazGrafica() {

    }

    public void conectarControlador(Controlador c) {
        this.controlador = c;
    }

    public void mostrar() {
        crear();
        ensamblar();
        this.setVisible(true);
    }

    private void crear() {
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
        separadorGroup = new ButtonGroup();
        dialogoArchivo = new FileChooser();
    }

    private void ensamblar() {
        this.setTitle("Estadistica Descriptiva");
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/estadisticadescriptiva/View/Escudo-ITL.jpg").getImage());

        panel.setLayout(null);
        otrossep.addActionListener(new OtroSeparadorClick());

        separador.setSelected(true);
        separador.addActionListener(new SeparadorClick());

        examinar.addActionListener(new ExaminarClick());
        cArchivo.setEnabled(false);
        panel.add(archivo);
        panel.add(examinar);
        panel.add(cArchivo);
        panel.add(separador);
        panel.add(separadores);
        panel.add(otrossep);
        panel.add(cSeparador);
        panel.add(correr);
        panel.add(fondo);

        separadorGroup.add(separador);

        separadorGroup.add(otrossep);

        fondo.setIcon(new ImageIcon("src/estadisticadescriptiva/View/banner2016.png"));
        fondo.setBounds(0, 0, 600, 73);
        fondo.setFocusable(false);

        archivo.setBounds(20, 83, 120, 30);

        examinar.setBounds(430, 123, 100, 30);

        cArchivo.setBounds(20, 123, 400, 30);

        separador.setBounds(20, 173, 100, 30);

        separadores.setBounds(20, 213, 200, 30);

        otrossep.setBounds(20, 300, 200, 30);

        cSeparador.setBounds(230, 300, 300, 30);
        cSeparador.setEnabled(false);
        correr.setBounds(430, 400, 100, 30);
        correr.addActionListener(new CorrerClick());
    }

    public void ponerSeparadoresDefecto(String[] listaSeparadores) {
        separadores.removeAllItems();
        for (String s : listaSeparadores) {
            separadores.addItem(s);
        }
    }

    public void colocarRuta(String s) {
        cArchivo.setText(s);
    }

    public void mandarMensaje(String s) {
        JOptionPane.showMessageDialog(this, s);
    }

    // -Manejo de eventos.
    private class SeparadorClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radio = (JRadioButton) e.getSource();
            //Implica que esta activada la primera opción.
            if (radio.isSelected()) {
                //Hay que desactivar el textField de la segunda opcion
                cSeparador.setEnabled(false);
                //Y activar el combo para esta opcion
                separadores.setEnabled(true);
            } else {
                //No hacemos nada...
            }
        }

    }

    private class OtroSeparadorClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radio = (JRadioButton) e.getSource();
            //Implica que esta activada la primera opción.
            if (radio.isSelected()) {
                //Hay que desactivar el combobox de la primera opcion
                separadores.setEnabled(false);
                //Y activar el JTextfield para este caso
                cSeparador.setEnabled(true);
            } else {
                //No hacemos nada...
            }
        }

    }

    private class ExaminarClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controlador.manejarExaminarClick();
        }

    }

    private class CorrerClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Verthissi si se haya seleccionado un archivo.
            if (separador.isSelected()) {
                controlador.manejarCorrerClick(true,
                        (String) separadores.getSelectedItem());
                
            }else{
                controlador.manejarCorrerClick(false, cSeparador.getText());
            }
        }

    }
}
