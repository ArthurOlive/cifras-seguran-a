package br.edu.ufersa.anselmos.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerLogger extends Remote {
    void log(String mensagem) throws RemoteException;
}
