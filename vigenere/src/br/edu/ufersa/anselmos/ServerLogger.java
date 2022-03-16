package br.edu.ufersa.anselmos;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerLogger extends Remote {
    void log(String mensagem) throws RemoteException;
}
