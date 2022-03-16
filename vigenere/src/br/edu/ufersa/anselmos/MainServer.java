package br.edu.ufersa.anselmos;

import br.edu.ufersa.anselmos.cifra.CifraVigenere;
import br.edu.ufersa.anselmos.config.CifraConfig;
import br.edu.ufersa.anselmos.config.ServerConfig;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MainServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        var cifra = new CifraVigenere(CifraConfig.CHAVE);
        var registry = LocateRegistry.createRegistry(ServerConfig.PORTA);
        var server = new ConsoleServerLogger(cifra);
        registry.bind(ServerConfig.NOME, server);
    }
}
