/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainserver.proxy;

import dto.AdminDTO;
import dto.MedicDTO;
import dto.PacientDTO;
import dto.Request;
import dto.Response;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Israel Dago
 */
public class MainServerProxy {

    private Socket socketServerAdmin;
    private Socket socketServerPeople;
    private ObjectOutputStream outServerAdmin;
    private ObjectOutputStream outServerPeople;
    private ObjectInputStream inServerAdmin;
    private ObjectInputStream inServerPeople;
    
    private MainServerProxy() {
        try {
            connectToServerAdmin();
            connectToServerPeople();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static final class SingletonHolder{
        private static final MainServerProxy SINGLETON = new MainServerProxy();
    }
    
    public static MainServerProxy getInstance(){
        return SingletonHolder.SINGLETON;
    }
    
    public boolean register(AdminDTO admin) throws IOException, ClassNotFoundException{
        Request request = new Request(Request.REGISTER);
            outServerAdmin.writeObject(request);
            outServerAdmin.writeObject(admin);
            Response resp = (Response) inServerAdmin.readObject();
            return resp.getCod() == Response.REGISTER_OK;
    }
    
    public AdminDTO login(AdminDTO admin) throws IOException, ClassNotFoundException{
        Request request = new Request(Request.LOGIN);
            outServerAdmin.writeObject(request);
            outServerAdmin.writeObject(admin);
            return (AdminDTO) inServerAdmin.readObject();
    }
    
    public void addMedic(MedicDTO medic) throws IOException {        
        outServerPeople.writeObject(new Request(Request.ADD_MEDIC));
        outServerPeople.writeObject(medic);
    }
    
    public void addPacient(PacientDTO pacient) throws IOException{
        outServerPeople.writeObject(new Request(Request.ADD_PACIENT));
        outServerPeople.writeObject(pacient);
    }
    
    
    
    
    
    ///////connexion to both servers
    private void connectToServerAdmin() throws IOException{
        this.socketServerAdmin = new Socket("localhost", 3232);
        outServerAdmin = new ObjectOutputStream(socketServerAdmin.getOutputStream());
        inServerAdmin = new ObjectInputStream(socketServerAdmin.getInputStream());
    }    
    private void connectToServerPeople() throws IOException{
        this.socketServerPeople= new Socket("localhost", 3233);
        outServerPeople = new ObjectOutputStream(socketServerPeople.getOutputStream());
        inServerPeople = new ObjectInputStream(socketServerPeople.getInputStream());   
    }    
    
    
    
}
