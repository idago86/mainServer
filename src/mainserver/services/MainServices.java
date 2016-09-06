/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainserver.services;

import dto.AdminDTO;
import dto.MedicDTO;
import dto.PacientDTO;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import mainserver.proxy.MainServerProxy;
import remote.InterfaceRemote;

/**
 *
 * @author Israel Dago
 */
public class MainServices extends UnicastRemoteObject implements InterfaceRemote{

    public MainServices() throws RemoteException {
    }

    @Override
    public boolean register(AdminDTO admin) throws IOException, ClassNotFoundException{
        return MainServerProxy.getInstance().register(admin);
    }
    
    @Override
    public AdminDTO login(AdminDTO admin) throws IOException, ClassNotFoundException {
        return MainServerProxy.getInstance().login(admin);
    }

    @Override
    public void addMedic(MedicDTO medic) throws IOException{
       MainServerProxy.getInstance().addMedic(medic);
    }

    @Override
    public void addPacient(PacientDTO pacient) throws IOException{
        MainServerProxy.getInstance().addPacient(pacient);
    }

    
    
    
    
}