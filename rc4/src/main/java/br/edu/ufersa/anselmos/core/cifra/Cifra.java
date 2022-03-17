package br.edu.ufersa.anselmos.core.cifra;

import java.util.Optional;

public interface Cifra {
    Optional<String> encriptar(String mensagem);

    Optional<String> decriptar(String mensagem);
}
