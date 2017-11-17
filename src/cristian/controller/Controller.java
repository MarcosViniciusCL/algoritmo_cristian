/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cristian.controller;

import cristian.model.ServerUDP;
import java.net.SocketException;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author aluno
 */
public class Controller implements Observer{
    private static Controller controller;
    private ServerUDP server;
    
    private Controller() throws SocketException{
        this.server = new ServerUDP(2525);
    }
    
    
    
    public static Controller getInstance() throws SocketException{
        if(Controller.controller == null){
            Controller.controller = new Controller();
            return Controller.controller;
        }
        return Controller.controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
