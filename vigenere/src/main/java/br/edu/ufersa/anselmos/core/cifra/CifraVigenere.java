package br.edu.ufersa.anselmos.core.cifra;

public class CifraVigenere implements Cifra {
    private static final int TAMANHO_DICIONARIO = 26;
    private final String palavraChave;

    public CifraVigenere(String palavraChave) {
        this.palavraChave = palavraChave;
    }

    private String palavraChaveExtendida(String mensagem) {
        var builder = new StringBuilder();
        var quantidadePalavrasChaveCompleta = mensagem.length() / palavraChave.length();
        builder.append(palavraChave.repeat(quantidadePalavrasChaveCompleta));
        var restoPalavraChave = mensagem.length() % palavraChave.length();
        builder.append(palavraChave, 0, restoPalavraChave);
        return builder.toString();
    }

    private char tabelaRasa(char caractereLinha, char caractereColuna) {
        int linha = caractereLinha - 'A';
        int coluna = caractereColuna - 'A';
        return (char) ((linha + coluna) % TAMANHO_DICIONARIO + 'A');
    }

    private char tabelaRasaInversa(char caractereLinha, char caractereFinal) {
        int linha = caractereLinha - 'A';
        int deslocamento = (caractereFinal - 'A' - linha) % TAMANHO_DICIONARIO;
        if (deslocamento < 0) {
            deslocamento += TAMANHO_DICIONARIO;
        }
        return (char) (deslocamento + 'A');
    }

    @Override
    public String encriptar(String mensagem) {
        var chave = palavraChaveExtendida(mensagem);
        var builder = new StringBuilder();
        for (var i = 0; i < mensagem.length(); i++) {
            builder.append(tabelaRasa(mensagem.charAt(i), chave.charAt(i)));
        }
        return builder.toString();
    }

    @Override
    public String decriptar(String mensagem) {
        var chave = palavraChaveExtendida(mensagem);
        var builder = new StringBuilder();
        for (var i = 0; i < mensagem.length(); i++) {
            builder.append(tabelaRasaInversa(chave.charAt(i), mensagem.charAt(i)));
        }
        return builder.toString();
    }
}