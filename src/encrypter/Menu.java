package encrypter;

import javax.crypto.SecretKey;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Menu {
    private KeyManager km;
    private FileEncryptor fe;
    private Scanner scanner;

    public Menu() {
        this.km = new KeyManager();
        this.fe = new FileEncryptor();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            exibirMenu();
            String opcao = scanner.nextLine();

            try {
                switch (opcao) {
                    case "1":
                        criptografar();
                        break;
                    case "2":
                        descriptografar();
                        break;
                    case "3":
                        System.out.println("\nSaindo... Até logo!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("\n❌ Erro: " + e.getMessage());
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║      ENCRIPTADOR AES-256       ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("1. Criptografar arquivo");
        System.out.println("2. Descriptografar arquivo");
        System.out.println("3. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    private void criptografar() throws Exception {
        System.out.print("\nDigite o caminho completo do arquivo: ");
        String caminho = scanner.nextLine();
        Path arquivo = Paths.get(caminho);

        System.out.println("\n⏳ Gerando/carregando chave...");
        SecretKey chave = km.generateKey();
        km.salvarKey(chave);

        System.out.println("⏳ Criptografando arquivo...");
        fe.criptografarArquivo(chave, arquivo);
        System.out.println("✅ Arquivo criptografado com sucesso!");
        System.out.println("📁 Chave salva em: C:\\Key-Encrypter\\chave_secreta.key");
    }

    private void descriptografar() throws Exception {
        System.out.print("\nDigite o caminho completo do arquivo: ");
        String caminho = scanner.nextLine();
        Path arquivo = Paths.get(caminho);

        System.out.println("\n⏳ Carregando chave...");
        SecretKey chave = km.carregarKey();

        System.out.println("⏳ Descriptografando arquivo...");
        fe.descriptografarArquivo(chave, arquivo);
        System.out.println("✅ Arquivo descriptografado com sucesso!");
    }
}