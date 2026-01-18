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
        Path arquivo = null;

        while (arquivo == null) {
            System.out.print("\nDigite o caminho completo do arquivo: ");
            String caminho = scanner.nextLine();

            try {
                arquivo = Paths.get(caminho);


                if (!java.nio.file.Files.exists(arquivo)) {
                    System.out.println("Arquivo não encontrado! Tente novamente.");
                    arquivo = null;
                }
            } catch (Exception e) {
                System.out.println("Caminho inválido! Tente novamente.");
                arquivo = null;
            }
        }
        SecretKey chave = km.carregarKey();
        fe.criptografarArquivo(chave, arquivo);
        System.out.println("Arquivo criptografado com sucesso!");
    }

    private void descriptografar() throws Exception {
        Path arquivo = null;

        // Loop até conseguir um caminho válido
        while (arquivo == null) {
            System.out.print("\nDigite o caminho completo do arquivo: ");
            String caminho = scanner.nextLine();

            try {
                arquivo = Paths.get(caminho);

                if (!java.nio.file.Files.exists(arquivo)) {
                    System.out.println("Arquivo não encontrado! Tente novamente.");
                    arquivo = null;
                }
            } catch (Exception e) {
                System.out.println("Caminho inválido! Tente novamente.");
                arquivo = null;
            }
        }
        SecretKey chave = km.carregarKey();
        fe.descriptografarArquivo(chave, arquivo);
        System.out.println("Arquivo descriptografado com sucesso!");
    }
}