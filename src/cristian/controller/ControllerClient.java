/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.controller;

import cristian.model.Mensagem;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class ControllerClient {

    private static ControllerClient controller;

    public static ControllerClient getInstance() {
        if (ControllerClient.controller == null) {
            ControllerClient.controller = new ControllerClient();
            return ControllerClient.controller;
        }
        return ControllerClient.controller;
    }

    public void gerarResultado(Mensagem mens) {

    }

    public String receberMensagem() {
//Converte o argumento recebido para inteiro (numero da porta)      

        //Cria o DatagramSocket para aguardar mensagens, neste momento o método fica bloqueando
        try {
            //até o recebimente de uma mensagem
            DatagramSocket ds = new DatagramSocket(Integer.parseInt("3434"));
            //Preparando o buffer de recebimento da mensagem
            byte[] msg = new byte[1024];
            //Prepara o pacote de dados
            DatagramPacket pkg = new DatagramPacket(msg, msg.length);
            //Recebimento da mensagem
            ds.receive(pkg);
            ds.close();
            return new String(pkg.getData()).trim();
        } catch (IOException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    public void atualizarHorario() {

        DatagramPacket pkg;
        try {
            String arg = "1:" + getHorario() + "";
            byte[] msg = arg.getBytes();
            //Monta o pacote a ser enviado
            pkg = new DatagramPacket(msg, msg.length, InetAddress.getByName("127.0.0.1"), 2525);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pkg);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Cria o DatagramSocket que será responsável por enviar a mensagem

        //Envia a mensagem
    }

    private long getHorario() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }
}
