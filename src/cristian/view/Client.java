/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.view;

import cristian.controller.ControllerClient;

/**
 *
 * @author marcos
 */
public class Client {
    public static void main (String args[]){
        ControllerClient controller = ControllerClient.getInstance();
        System.out.println(controller.atualizarHorario());
    }
}
