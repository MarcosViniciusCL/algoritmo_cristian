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
    
    public void enviarMensagem(Mensagem mensagem) throws IOException{
        String mens = mensagem.getProtocolo() + ":" + mensagem.getMensagem();
        byte[] env = mens.getBytes();
        DatagramPacket pkg = new DatagramPacket(env, env.length, mensagem.getEndereco(), 3434);
        socket.send(pkg);
    }

    private void receberMensagem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (monitorando) {
                    try {
                        byte[] mens = new byte[1024];
                        DatagramPacket pacote = new DatagramPacket(mens, mens.length);
                        socket.receive(pacote);
                        String receb[] = new String(pacote.getData()).split(":");
                        mensagens.add(new Mensagem(pacote.getAddress(), receb[0], Integer.parseInt(receb[1])));
                        setChanged();
                        notifyObservers(mensagens);
                    } catch (IOException ex) {
                        Logger.getLogger(ServerUDP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }).start();

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
