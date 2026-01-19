package encrypter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import javax.crypto.SecretKey;

public class EncriptadorGUI extends JFrame {
    private KeyManager km;
    private FileEncryptor fe;
    private JLabel arquivoLabel;
    private JButton importarBtn;
    private JRadioButton criptografarRadio;
    private JRadioButton descriptografarRadio;
    private JButton executarBtn;
    private JTextArea logArea;
    private File arquivoSelecionado;

    public EncriptadorGUI() {
        this.km = new KeyManager();
        this.fe = new FileEncryptor();

        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("Encriptador AES-256");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(new Color(240, 240, 245));

        // Painel do topo com título
        JPanel painelTitulo = criarPainelTitulo();
        painelPrincipal.add(painelTitulo, BorderLayout.NORTH);

        // Painel central com opções
        JPanel painelCentral = criarPainelCentral();
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // Painel inferior com log
        JPanel painelLog = criarPainelLog();
        painelPrincipal.add(painelLog, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(240, 240, 245));

        JLabel titulo = new JLabel("ENCRIPTADOR AES-256");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(new Color(50, 50, 50));
        painel.add(titulo);

        return painel;
    }

    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBackground(new Color(240, 240, 245));
        painel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Seção de seleção de operação
        JPanel painelOperacao = new JPanel();
        painelOperacao.setBackground(Color.WHITE);
        painelOperacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(15, 20, 15, 20)
        ));
        painelOperacao.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        painelOperacao.setMaximumSize(new Dimension(550, 80));

        JLabel operacaoLabel = new JLabel("Selecione a operação:");
        operacaoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        painelOperacao.add(operacaoLabel);

        criptografarRadio = new JRadioButton("Criptografar");
        criptografarRadio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        criptografarRadio.setBackground(Color.WHITE);
        criptografarRadio.setSelected(true);

        descriptografarRadio = new JRadioButton("Descriptografar");
        descriptografarRadio.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descriptografarRadio.setBackground(Color.WHITE);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(criptografarRadio);
        grupo.add(descriptografarRadio);

        painelOperacao.add(criptografarRadio);
        painelOperacao.add(descriptografarRadio);

        painel.add(painelOperacao);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Seção de seleção de arquivo
        JPanel painelArquivo = new JPanel();
        painelArquivo.setBackground(Color.WHITE);
        painelArquivo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                new EmptyBorder(15, 20, 15, 20)
        ));
        painelArquivo.setLayout(new BorderLayout(10, 10));
        painelArquivo.setMaximumSize(new Dimension(550, 120));

        JLabel arquivoTitulo = new JLabel("Arquivo:");
        arquivoTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));

        arquivoLabel = new JLabel("Nenhum arquivo selecionado");
        arquivoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        arquivoLabel.setForeground(new Color(100, 100, 100));

        importarBtn = new JButton("Importar Arquivo");
        importarBtn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        importarBtn.setBackground(new Color(70, 130, 180));
        importarBtn.setForeground(Color.WHITE);
        importarBtn.setFocusPainted(false);
        importarBtn.setBorder(new EmptyBorder(10, 20, 10, 20));
        importarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        importarBtn.addActionListener(e -> importarArquivo());

        JPanel painelArquivoInfo = new JPanel();
        painelArquivoInfo.setLayout(new BorderLayout(5, 5));
        painelArquivoInfo.setBackground(Color.WHITE);
        painelArquivoInfo.add(arquivoTitulo, BorderLayout.NORTH);
        painelArquivoInfo.add(arquivoLabel, BorderLayout.CENTER);

        painelArquivo.add(painelArquivoInfo, BorderLayout.CENTER);
        painelArquivo.add(importarBtn, BorderLayout.EAST);

        painel.add(painelArquivo);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Botão executar
        executarBtn = new JButton("EXECUTAR");
        executarBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        executarBtn.setBackground(new Color(46, 125, 50));
        executarBtn.setForeground(Color.WHITE);
        executarBtn.setFocusPainted(false);
        executarBtn.setBorder(new EmptyBorder(15, 40, 15, 40));
        executarBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        executarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        executarBtn.setEnabled(false);
        executarBtn.addActionListener(e -> executarOperacao());

        painel.add(executarBtn);

        return painel;
    }

    private JPanel criarPainelLog() {
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(5, 5));
        painel.setBackground(new Color(240, 240, 245));

        JLabel logLabel = new JLabel("Log:");
        logLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        logLabel.setForeground(new Color(80, 80, 80));

        logArea = new JTextArea(6, 40);
        logArea.setFont(new Font("Consolas", Font.PLAIN, 11));
        logArea.setEditable(false);
        logArea.setBackground(new Color(250, 250, 250));
        logArea.setBorder(new EmptyBorder(8, 8, 8, 8));

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        painel.add(logLabel, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);

        return painel;
    }

    private void importarArquivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int resultado = fileChooser.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            arquivoSelecionado = fileChooser.getSelectedFile();
            arquivoLabel.setText(arquivoSelecionado.getName());
            arquivoLabel.setForeground(new Color(50, 50, 50));
            executarBtn.setEnabled(true);
            adicionarLog("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());
        }
    }

    private void executarOperacao() {
        if (arquivoSelecionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, selecione um arquivo!",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        executarBtn.setEnabled(false);
        importarBtn.setEnabled(false);

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    publish("Carregando/Gerando chave AES-256...");
                    SecretKey chave = km.carregarKey();

                    if (criptografarRadio.isSelected()) {
                        publish("Criptografando arquivo...");
                        fe.criptografarArquivo(chave, arquivoSelecionado.toPath());
                        publish("Arquivo criptografado com sucesso!");
                        publish("Salvo como: " + arquivoSelecionado.getName() + ".encrypted");
                    } else {
                        publish("Descriptografando arquivo...");
                        fe.descriptografarArquivo(chave, arquivoSelecionado.toPath());
                        publish("Arquivo descriptografado com sucesso!");
                        publish("Salvo como: " + arquivoSelecionado.getName().replace(".encrypted", "") + ".decrypted");
                    }
                } catch (Exception e) {
                    publish("ERRO: " + e.getMessage());
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String msg : chunks) {
                    adicionarLog(msg);
                }
            }

            @Override
            protected void done() {
                executarBtn.setEnabled(true);
                importarBtn.setEnabled(true);
            }
        };

        worker.execute();
    }

    private void adicionarLog(String mensagem) {
        logArea.append("[" + java.time.LocalTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")) + "] " + mensagem + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}