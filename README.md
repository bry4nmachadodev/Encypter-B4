# Encriptador AES-256

Um sistema robusto de criptografia de arquivos implementado em Java, utilizando o algoritmo AES-256 para garantir a segurança e confidencialidade dos seus dados.

## Visão Geral

Este projeto oferece uma solução completa para criptografia e descriptografia de arquivos através de uma interface de linha de comando intuitiva. Desenvolvido com foco em segurança e facilidade de uso, o sistema gerencia automaticamente as chaves criptográficas e oferece feedback claro durante todo o processo.

## Características Principais

**Segurança Robusta**
- Algoritmo AES-256, padrão da indústria para criptografia simétrica
- Gerenciamento automático de chaves criptográficas
- Proteção contra perda de chaves através de persistência em arquivo

**Facilidade de Uso**
- Interface de menu interativa e amigável
- Validação automática de caminhos de arquivo
- Mensagens claras de status e erro
- Processo simplificado em poucos passos

**Confiabilidade**
- Tratamento robusto de exceções
- Validação de entrada do usuário
- Verificação de existência de arquivos
- Geração e carregamento inteligente de chaves

## Estrutura do Projeto

```
encriptador-bryan/
│
├── src/
│   ├── Main.java
│   └── encrypter/
│       ├── Menu.java
│       ├── KeyManager.java
│       └── FileEncryptor.java
│
└── C:/Key-Encrypter/
    └── chave_secreta.key
```

## Requisitos do Sistema

- Java Development Kit (JDK) 17 ou superior
- Sistema operacional: Windows, Linux ou macOS
- Permissões de leitura/escrita no sistema de arquivos

## Instalação

### Clonar o Repositório

```bash
git clone https://github.com/seu-usuario/encriptador-bryan.git
cd encriptador-bryan
```

### Compilar o Projeto

```bash
javac -d out/production/encriptador-bryan src/Main.java src/encrypter/*.java
```

### Executar a Aplicação

```bash
java -cp out/production/encriptador-bryan Main
```

## Guia de Uso

### Primeira Execução

Na primeira vez que você executar o programa, uma chave AES-256 será gerada automaticamente e salva em:
```
C:\Key-Encrypter\chave_secreta.key
```

**IMPORTANTE**: Esta chave é essencial para descriptografar seus arquivos. Faça backup dela em um local seguro.

### Criptografar um Arquivo

1. Selecione a opção `1` no menu principal
2. Digite o caminho completo do arquivo que deseja criptografar
3. O sistema carregará ou gerará a chave automaticamente
4. O arquivo será criptografado e salvo com a extensão `.encrypted`

**Exemplo:**
```
Digite o caminho completo do arquivo: C:\Users\Documents\documento.txt
```

Resultado: `C:\Users\Documents\documento.txt.encrypted`

### Descriptografar um Arquivo

1. Selecione a opção `2` no menu principal
2. Digite o caminho completo do arquivo criptografado (geralmente com extensão `.encrypted`)
3. O sistema carregará a chave salva
4. O arquivo será descriptografado e salvo com a extensão `.decrypted`

**Exemplo:**
```
Digite o caminho completo do arquivo: C:\Users\Documents\documento.txt.encrypted
```

Resultado: `C:\Users\Documents\documento.txt.decrypted`

## Fluxo de Operação

### Criptografia

```
┌─────────────────────┐
│  Arquivo Original   │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Carregar/Gerar     │
│  Chave AES-256      │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Algoritmo AES      │
│  Modo Encrypt       │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Arquivo            │
│  .encrypted         │
└─────────────────────┘
```

### Descriptografia

```
┌─────────────────────┐
│  Arquivo            │
│  .encrypted         │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Carregar Chave     │
│  Salva              │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Algoritmo AES      │
│  Modo Decrypt       │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│  Arquivo            │
│  .decrypted         │
└─────────────────────┘
```

## Segurança

### Boas Práticas

**Gerenciamento de Chaves**
- Faça backup da chave criptográfica em múltiplos locais seguros
- Nunca compartilhe sua chave por canais não seguros
- Considere usar um gerenciador de senhas para armazenar a chave
- Mantenha cópias offline da chave em dispositivos físicos seguros

**Uso do Sistema**
- Sempre verifique se a criptografia foi bem-sucedida antes de deletar arquivos originais
- Teste a descriptografia em arquivos de baixa importância primeiro
- Mantenha backups dos arquivos originais importantes
- Use caminhos absolutos para evitar erros de localização

## Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

### Diretrizes de Código

- Mantenha a consistência com o estilo de código existente
- Adicione comentários para código complexo
- Teste todas as funcionalidades antes de submeter
- Atualize a documentação conforme necessário

## Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para detalhes.

## Autor

Desenvolvido por Bryan

## Suporte

Para reportar bugs ou solicitar novas funcionalidades, abra uma issue no repositório do GitHub.

---

**Aviso Legal**: Este software é fornecido "como está", sem garantias de qualquer tipo. Use por sua conta e risco. Sempre mantenha backups de seus dados importantes.
