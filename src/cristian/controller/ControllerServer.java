/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.controller;

import cristian.model.Protocolo;
import cristian.model.Mensagem;
import cristian.model.ServerUDP;
import java.io.IOException;
import java.net.SocketException;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class ControllerServer implements Observer {

    private static ControllerServer controller;
    private ServerUDP server;

    private ControllerServer() {
        try {
            this.server = new ServerUDP(2525);
            this.server.addObserver(this);
        } catch (SocketException ex) {
            Logger.getLogger(ControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mandarResposta(Mensagem mens) {
        try {
            mens.adicionarMensagem(getHorario() + "");
            Thread.sleep(3000);
            mens.adicionarMensagem(getHorario() + "");
            mens.setProtocolo(2);
            this.server.enviarMensagem(mens);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ControllerServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private long getHorario() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public static ControllerServer getInstance() {
        if (ControllerServer.controller == null) {
            ControllerServer.controller = new ControllerServer();
            return ControllerServer.controller;
        }
        return ControllerServer.controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        List<Mensagem> temp = (List<Mensagem>) arg;
        Mensagem mens = temp.remove(temp.size() - 1);
        Protocolo.novoComando(mens);
    }

}
