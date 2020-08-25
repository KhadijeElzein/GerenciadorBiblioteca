package br.unioeste.biblioteca;

public class Main {
    static final String NOME_ARQUIVO = "arquivo.txt";
    public static void main(String[] args) {
        var arquivo = new Arquivo(NOME_ARQUIVO);
        arquivo.lerArquivo();
        arquivo.escreverArquivo();
    }
}
