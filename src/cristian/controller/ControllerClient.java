/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.controller;

import cristian.model.Mensagem;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author marcos
 */
public class ControllerClient {

    private static ControllerClient controller;
    DatagramSocket socket;

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
            byte[] msg = new byte[1024];
            //Prepara o pacote de dados
            DatagramPacket pkg = new DatagramPacket(msg, msg.length);
            //Recebimento da mensagem
            System.out.println("Tamanho: " + socket.getReceiveBufferSize());
            socket.receive(pkg);
            socket.close();
            return new String(pkg.getData()).trim();
        } catch (IOException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public String atualizarHorario() {

        DatagramPacket pkg;
        try {
            String arg = "1:350";//"1:" + getHorario() + "";
            byte[] msg = arg.getBytes();
            //Monta o pacote a ser enviado
            pkg = new DatagramPacket(msg, msg.length, InetAddress.getByName(JOptionPane.showInputDialog("IP Server").trim()), 2525);
            socket = new DatagramSocket(3434);
            socket.send(pkg);
            arg = receberMensagem();
         
            System.out.println(arg);
            alg_cristian(arg);
        } catch (UnknownHostException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ControllerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private long getHorario() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    private void alg_cristian(String arg) {
        String respa[] = arg.split(":");
        String respb[] = respa[1].split(",");
        int a = Integer.parseInt(respb[0].trim());
        int x = Integer.parseInt(respb[1].trim());
        int y = Integer.parseInt(respb[2].trim());
        int b = Integer.parseInt(respb[3].trim());
        System.out.println("A: " + a + "\nX: " + x + "\nY: " + y + "\nB: " + b);
        int delay = (b - a) - (y - x);
        int deslocamento = (y + delay / 2) - b;
        System.out.println("Delay: " + delay);
        System.out.println("Deslocamento: " + deslocamento);

    }
}
