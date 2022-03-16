package br.edu.ufersa.anselmos.core.cifra;

import java.nio.charset.StandardCharsets;

public class CifraVernam implements Cifra {
    private final String palavraChave;

    public CifraVernam(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    private String mensagemExtendida(String mensagem) {
        var builder = new StringBuilder();
        var quantidadeMensagensCompletas = palavraChave.length() / mensagem.length();
        builder.append(mensagem.repeat(quantidadeMensagensCompletas));
        var restoMensagem = palavraChave.length() % mensagem.length();
        builder.append(mensagem, 0, restoMensagem);
        return builder.toString();
    }

    @Override
    public String encriptar(String mensagem) {
        var bytesChave = palavraChave.getBytes(StandardCharsets.UTF_8);
        var bytesMensagem = mensagemExtendida(mensagem).getBytes(StandardCharsets.UTF_8);
        var bytesFinal = new byte[bytesChave.length];
        for (var i = 0; i < bytesChave.length; i++) {
            bytesFinal[i] = (byte) (bytesChave[i] ^ bytesMensagem[i]);
        }
        return new String(bytesFinal, StandardCharsets.UTF_8);
    }

    @Override
    public String decriptar(String mensagem) {
        return encriptar(mensagem);
    }
}