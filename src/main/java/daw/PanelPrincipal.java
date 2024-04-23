/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author antonio
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    // Atributos de la clase (privados)
    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;
    private double operando1;
    private double operando2;

    // Constructor
    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1; // No hay operaciones en la calculadora
        operando1 = 0;
        operando2 = 0;
    }

    // Se inicializan los componentes gráficos y se colocan en el panel
    private void initComponents() {
        // Creamos el panel de botones
        botonera = new PanelBotones();
        // Creamos el área de texto
        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.lightGray);

        //Establecemos layout del panel principal
        this.setLayout(new BorderLayout());
        // Colocamos la botonera y el área texto
        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        for (JButton boton : this.botonera.getgrupoBotones()) {
            boton.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Se obtiene el objeto que desencadena el evento
        Object o = ae.getSource();
        // Si es un botón
        if (o instanceof JButton jButton) {
            String textoBoton = jButton.getText();
            if (textoBoton.matches("[0-9]+")) {
                areaTexto.append(textoBoton);
            } else if (textoBoton.matches("[+\\-*/]")) {
                operando1 = Double.parseDouble(areaTexto.getText());
                tipoOperacion = obtenerTipoOperacion(textoBoton);
                areaTexto.setText("");
            } else if (textoBoton.equals("=")) {
                operando2 = Double.parseDouble(areaTexto.getText());
                double resultado = realizarOperacion(operando1, operando2, tipoOperacion);
                areaTexto.setText(String.valueOf(resultado));
            } else if (textoBoton.equals("C")) {
                areaTexto.setText("");
            }
        }
    }

    private int obtenerTipoOperacion(String operacion) {
        return switch (operacion) {
            case "+" ->
                0;
            case "-" ->
                1;
            case "*" ->
                2;
            case "/" ->
                3;
            default ->
                -1;
        };
    }

    private double realizarOperacion(double num1, double num2, int tipoOperacion) {
        switch (tipoOperacion) {
            case 0 -> {
                return num1 + num2;
            }
            case 1 -> {
                return num1 - num2;
            }
            case 2 -> {
                return num1 * num2;
            }
            case 3 -> {
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Error");
                    return 0;
                } else {
                    return num1 / num2;
                }
            }
            default -> {
                return 0;
            }
        }
    }

}
