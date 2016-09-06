/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainserver;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import mainserver.services.MainServices;

/**
 *
 * @author Israel Dago
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registru = LocateRegistry.createRegistry(4545);
            registru.rebind("spitalServer", new MainServices());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
