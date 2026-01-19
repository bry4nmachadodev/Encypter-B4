package encrypter;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

public class KeyManager {

    //gerar chave AES -> (responsável por criptografar e descriptografar)
    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey chaveGerada = keyGenerator.generateKey();

        return chaveGerada;
    }

    //criar arquivo -> com a chave AES para descriptografar
    public void salvarKey(SecretKey key) throws IOException {
        Path path = Paths.get("C:\\Key-Encrypter\\chave_secreta.key");

        if (Files.exists(path)) {
            System.out.println("Chave já existe em: " + path.toAbsolutePath());
            return;
        }

        byte[] keyBytes = key.getEncoded();
        Files.createDirectories(path.getParent());
        Files.write(path, keyBytes);

        System.out.println("Chave salva em: " + path.toAbsolutePath());
    }

    //ler arquivo -> que está com a chave
    public SecretKey carregarKey() throws Exception {
        Path path = Paths.get("C:\\Key-Encrypter\\chave_secreta.key");

        if (!Files.exists(path)) {
            System.out.println("Chave não encontrada. Gerando nova chave...");
            SecretKey novaChave = generateKey();
            salvarKey(novaChave);
            return novaChave;
        }

        System.out.println("Carregando chave existente...");
        return new SecretKeySpec(Files.readAllBytes(path), "AES");
    }

}