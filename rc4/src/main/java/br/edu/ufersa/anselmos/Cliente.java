package br.edu.ufersa.anselmos;

import br.edu.ufersa.anselmos.core.LoremIpsumGenerator;
import br.edu.ufersa.anselmos.core.ServerLogger;
import br.edu.ufersa.anselmos.core.cifra.Cifra;

import java.rmi.RemoteException;

public class Cliente implements Runnable {
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
        System.out.println("Started");
        loremIpsumGenerator.generate().forEach((palavra) -> cifra.encriptar(palavra).ifPresent(mensagemCifrada -> {
            System.out.println(palavra);
            try {
                serverLogger.log(mensagemCifrada);
                Thread.sleep(1000);
            } catch (RemoteException | InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }));
        System.out.println("Stopped");
    }
}
