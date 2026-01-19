# Encriptador AES-256

Sistema de criptografia de arquivos usando o algoritmo AES-256 bit.

## Versões Disponíveis

### v2.0.0 - Interface Gráfica (GUI) - Atual
Interface moderna com tema dark para criptografar e descriptografar arquivos de forma visual.

**Como usar:**
1. Execute o aplicativo
2. Selecione a operação (Criptografar ou Descriptografar)
3. Clique em "Importar Arquivo" e escolha o arquivo
4. Clique em "EXECUTAR"
5. Acompanhe o progresso no log

### v1.0.0 - Interface de Linha de Comando (CLI)
Versão minimalista via terminal para quem prefere linha de comando.

**Como usar:**
1. Execute o programa
2. Digite a opção desejada (1 ou 2)
3. Informe o caminho completo do arquivo
4. O programa processará automaticamente

## Download

Acesse a seção [Releases](../../releases) e baixe a versão desejada:
- **v2.0.0**: Recomendada para uso geral (GUI)
- **v1.0.0**: Para usuários avançados ou automação (CLI)

## Requisitos

- Java 17 ou superior
- Windows, Linux ou macOS

## Executar

```bash
java -jar encriptador-v2.0.jar
```

## Importante

A chave de criptografia é salva automaticamente em:
```
C:\Key-Encrypter\chave_secreta.key
```

**Faça backup desta chave!** Sem ela, não será possível descriptografar seus arquivos.

## Tecnologias

- Java 17
- Swing (GUI)
- AES-256 (Criptografia)
- KeyGenerator (Geração de chaves)

## Autor

Desenvolvido por Bryan

## Licença

MIT License
