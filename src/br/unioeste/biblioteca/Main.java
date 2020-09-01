package br.unioeste.biblioteca;

import br.unioeste.biblioteca.domain.Estante;
import br.unioeste.biblioteca.domain.arquivo.ArquivoEntrada;
import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

public class Main {
    static final String NOME_ARQUIVO = "arquivo.txt";
    public static void main(String[] args) {
        ArquivoEntrada arquivo = new ArquivoEntrada(NOME_ARQUIVO);
        ListaDuplamenteEncadeada<Estante> biblioteca = arquivo.lerArquivo();
        biblioteca.imprime(biblioteca);
        System.out.println("Test");
    }
}
