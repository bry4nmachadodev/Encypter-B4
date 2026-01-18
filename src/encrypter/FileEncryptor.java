package encrypter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileEncryptor {
    private KeyManager keyManager;

    //Criptografar arquivo -> chave AES
    public void criptografarArquivo(SecretKey chave, Path arquivoOrigem) throws Exception {
        //lê o arquivo recebido
        byte[] dados = Files.readAllBytes(arquivoOrigem);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] dadosCriptografado = cipher.doFinal(dados);

        Files.write(arquivoOrigem, dadosCriptografado);
    }

    //Descriptografar arquivo -> chave AES
    public void descriptografarArquivo(SecretKey chave, Path arquivoOrigem) throws Exception {
        //lê o arquivo recebido
        byte[] dados = Files.readAllBytes(arquivoOrigem);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] dadosCriptografado = cipher.doFinal(dados);

        Files.write(arquivoOrigem, dadosCriptografado);
    }

}
