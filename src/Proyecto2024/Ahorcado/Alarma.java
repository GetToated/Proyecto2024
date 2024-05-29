package Proyecto2024.Ahorcado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Alarma implements Interfaz {

    private JFrame frame;
    private JLabel mensajeLabel;

    public Alarma() {

        frame = new JFrame();
        frame.setTitle("Alarma");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        mensajeLabel = new JLabel("¡Tiempo agotado!");
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 20));


        JButton cerrarButton = new JButton("Cerrar Alarma");
        cerrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(mensajeLabel, BorderLayout.CENTER);
        frame.add(cerrarButton, BorderLayout.SOUTH);
    }

    @Override
    public void sonar() {

        frame.setVisible(true);
    }
}
