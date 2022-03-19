package br.edu.ufersa.anselmos;


import br.edu.ufersa.anselmos.core.LoremIpsumGenerator;
import br.edu.ufersa.anselmos.core.ServerLogger;
import br.edu.ufersa.anselmos.core.cifra.Cifra;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente implements Runnable {
    private final Logger logger = Logger.getLogger(Cliente.class.getName());
    private final ServerLogger serverLogger;
    private final Cifra cifra;
    private final LoremIpsumGenerator loremIpsumGenerator;

    public Cliente(ServerLogger serverLogger, Cifra cifra, LoremIpsumGenerator loremIpsumGenerator) {
        this.serverLogger = serverLogger;
        this.cifra = cifra;
        this.loremIpsumGenerator = loremIpsumGenerator;
    }

    @Override
    public void run() {
        logger.log(Level.FINE, "Started");
        loremIpsumGenerator.generate().forEach((palavra) -> {
            var encriptado = cifra.encriptar(palavra);
            try {
                serverLogger.log(encriptado);
                Thread.sleep(1000);
            } catch (RemoteException | InterruptedException e) {
                logger.log(Level.WARNING, e.getMessage());
            }
        });
        logger.log(Level.FINE, "Stopped");
    }
}
