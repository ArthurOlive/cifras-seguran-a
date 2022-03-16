package br.edu.ufersa.anselmos;

import br.edu.ufersa.anselmos.core.ServerLogger;
import br.edu.ufersa.anselmos.core.cifra.Cifra;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsoleServerLogger extends UnicastRemoteObject implements ServerLogger {
    private final Cifra cifra;
    private final Logger logger = Logger.getLogger(ConsoleServerLogger.class.getName());

    public ConsoleServerLogger(Cifra cifra) throws RemoteException {
        super();
        this.cifra = cifra;
    }

    @Override
    public void log(String mensagemCifrada) throws RemoteException {
        var mensagem = cifra.decriptar(mensagemCifrada);
        logger.log(Level.INFO, mensagem);
    }
}
