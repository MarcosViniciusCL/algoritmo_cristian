/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.model;

import cristian.controller.ControllerClient;
import cristian.controller.ControllerServer;

/**
 *
 * @author marcos
 */
public class Protocolo {
    private static final int solicitaHorario = 1;
    private static final int responderHorario = 2;
    private static final ControllerServer controllerServer = ControllerServer.getInstance();
    private static final ControllerClient controllerClient = ControllerClient.getInstance();
    

    public static void novoComando(Mensagem mens) {
        if(mens.getProtocolo() == solicitaHorario){
            controllerServer.mandarResposta(mens);
        } else if (mens.getProtocolo() == responderHorario){
            controllerClient.gerarResultado(mens);
        }
    }
    
}
