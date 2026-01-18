package encrypter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class KeyManager {

    //gerar chave AES -> (responsável por criptografar e descriptografar)
    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey chaveGerada = keyGenerator.generateKey();

        return chaveGerada;
    }
}
