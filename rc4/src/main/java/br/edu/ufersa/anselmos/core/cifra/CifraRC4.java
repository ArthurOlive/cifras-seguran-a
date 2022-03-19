package br.edu.ufersa.anselmos.core.cifra;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Optional;

public class CifraRC4 implements Cifra {
    private final String secretKey;
    private final String salt;
    private final Base64.Encoder encoder;
    private final Base64.Decoder decoder;

    public CifraRC4(String secretKey, String salt, Base64.Encoder encoder, Base64.Decoder decoder) {
        this.secretKey = secretKey;
        this.salt = salt;
        this.encoder = encoder;
        this.decoder = decoder;
    }

    private Cipher buildCipher(int opMode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "ARCFOUR");
        var cipher = Cipher.getInstance("ARCFOUR");
        cipher.init(opMode, secretKeySpec);
        return cipher;
    }

    @Override
    public Optional<String> encriptar(String mensagem) {
        try {
            var cipher = buildCipher(Cipher.ENCRYPT_MODE);
            var mensagemCifrada = encoder.encodeToString(cipher.doFinal(mensagem.getBytes(StandardCharsets.UTF_8)));
            return Optional.of(mensagemCifrada);
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | BadPaddingException | InvalidKeySpecException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> decriptar(String mensagemCifrada) {
        try {
            var cipher = buildCipher(Cipher.DECRYPT_MODE);
            var mensagem = new String(cipher.doFinal(decoder.decode(mensagemCifrada)));
            return Optional.of(mensagem);
        } catch (InvalidAlgorithmParameterException | BadPaddingException | InvalidKeyException | InvalidKeySpecException | NoSuchAlgorithmException | IllegalBlockSizeException | NoSuchPaddingException e) {
            return Optional.empty();
        }
    }
}
