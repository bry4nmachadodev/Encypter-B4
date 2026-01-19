package encrypter;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEncryptor {

    //Criptografar arquivo -> chave AES
    public void criptografarArquivo(SecretKey chave, Path arquivoOrigem) throws Exception {
        byte[] dados = Files.readAllBytes(arquivoOrigem);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] dadosCriptografado = cipher.doFinal(dados);

        // Cria arquivo com extensão .encrypted
        Path arquivoCriptografado = Paths.get(arquivoOrigem.toString() + ".encrypted");
        Files.write(arquivoCriptografado, dadosCriptografado);
    }

    //Descriptografar arquivo -> chave AES
    public void descriptografarArquivo(SecretKey chave, Path arquivoOrigem) throws Exception {
        byte[] dados = Files.readAllBytes(arquivoOrigem);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] dadosDescriptografado = cipher.doFinal(dados);

        // Remove o .encrypted do nome
        String caminhoOriginal = arquivoOrigem.toString().replace(".encrypted", "");
        Path arquivoDescriptografado = Paths.get(caminhoOriginal + ".decrypted");
        Files.write(arquivoDescriptografado, dadosDescriptografado);
    }

}