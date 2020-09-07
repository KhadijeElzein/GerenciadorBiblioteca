package br.unioeste.biblioteca;

import br.unioeste.biblioteca.domain.Aluno;
import br.unioeste.biblioteca.domain.Biblioteca;
import br.unioeste.biblioteca.domain.arquivo.ArquivoEntrada;
import br.unioeste.biblioteca.domain.estruturas.Fila;

public class Main {
    static final String NOME_ARQUIVO = "arquivo.txt";
    public static void main(String[] args) {
        ArquivoEntrada arquivo = new ArquivoEntrada(NOME_ARQUIVO);
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setEstantes(arquivo.lerArquivo());
        biblioteca.getEstantes().imprime(biblioteca.getEstantes());
        System.out.println("Test");
        
        biblioteca.inserirLivroBiblioteca(biblioteca.getEstantes(), 10l, "teste", "teste", 0l, 0l);
        biblioteca.imprimirMapaEstantes(biblioteca.getEstantes());
        biblioteca.retirarLivro(biblioteca.getEstantes(), 0l);
        Fila<Aluno> alunos = new Fila<Aluno>();
        Aluno a = new Aluno();
        a.setRa(1l);
        Aluno a2 = new Aluno();
        a2.setRa(22l);
        alunos.queue(alunos, a);
        alunos.queue(alunos, a2);
        biblioteca.imprimirMapaEstantes(biblioteca.getEstantes());
        biblioteca.imprimirFilaEsperaSala(alunos);

    }
}
