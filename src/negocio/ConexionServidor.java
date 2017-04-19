/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextField;

/**
 *
 * @author César
 */
public class ConexionServidor implements ActionListener {
    
    private Socket socket; 
    private JTextField tfMensaje;
    private String nombre;
    private String contraseña;
    private DataOutputStream salidaDatos;
    
    public ConexionServidor(Socket socket, JTextField tfMensaje, String nombre, String contraseña) {
        this.socket = socket;
        this.tfMensaje = tfMensaje;
        this.nombre = nombre;
        this.contraseña = contraseña;
        try {
            this.salidaDatos = new DataOutputStream(socket.getOutputStream());
            salidaDatos.writeUTF("validar");
            salidaDatos.writeUTF(nombre);
            salidaDatos.writeUTF(contraseña);
        } catch (IOException ex) {
            System.err.println("Error al crear el stream de salida : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.err.println("El socket no se creo correctamente. ");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            salidaDatos.writeUTF(tfMensaje.getText() );
            tfMensaje.setText("");
        } catch (IOException ex) {
            System.err.println("Error al intentar enviar un mensaje: " + ex.getMessage());
        }
    }

}
