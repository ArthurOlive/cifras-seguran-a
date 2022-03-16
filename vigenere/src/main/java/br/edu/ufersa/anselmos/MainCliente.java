package br.edu.ufersa.anselmos;

import br.edu.ufersa.anselmos.cifra.CifraVigenere;
import br.edu.ufersa.anselmos.config.CifraConfig;
import br.edu.ufersa.anselmos.config.ServerConfig;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainCliente {
    private static final Logger logger = Logger.getLogger(MainCliente.class.getName());

    public static void main2(String[] args) {
        var cifra = new CifraVigenere(CifraConfig.CHAVE);
        System.out.println(cifra.encriptar(""));
    }

    public static void main(String[] args) {
        var cifra = new CifraVigenere(CifraConfig.CHAVE);
        var loremIpsumGenerator = new LoremIpsumGenerator();
        try {
            var registry = LocateRegistry.getRegistry(ServerConfig.PORTA);
            var server = (ServerLogger) registry.lookup(ServerConfig.NOME);
            var cliente = new Cliente(server, cifra, loremIpsumGenerator);
            var executors = Executors.newCachedThreadPool();
            executors.submit(cliente);
        } catch (RemoteException e) {
            logger.log(Level.SEVERE, "Não foi possível obter o registry");
        } catch (NotBoundException e) {
            logger.log(Level.SEVERE, "Não foi possível encontrar o servidor no registry");
        }

    }
}
