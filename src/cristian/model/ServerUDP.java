/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class ServerUDP extends Observable {

    private DatagramSocket socket;
    private boolean monitorando;
    private List<Mensagem> mensagens;

    public ServerUDP(int porta) throws SocketException {
        this.socket = new DatagramSocket(porta);
        this.mensagens = new ArrayList();
    }

    public void receberMensagem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (monitorando) {
                    try {
                        byte[] mens = new byte[1024];
                        DatagramPacket pacote = new DatagramPacket(mens, mens.length);
                        socket.receive(pacote);
                        mensagens.add(new Mensagem(pacote.getAddress(), new String(pacote.getData())));
                        setChange();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });

    }

    public boolean isMonitorando() {
        return monitorando;
    }

    public void setMonitorando(boolean monitorando) {
        this.monitorando = monitorando;
        if (monitorando) {
            receberMensagem();
        }
    }

}
