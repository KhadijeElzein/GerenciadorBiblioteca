package br.unioeste.biblioteca;

import br.unioeste.biblioteca.domain.Aluno;
import br.unioeste.biblioteca.domain.Biblioteca;
import br.unioeste.biblioteca.domain.SalaEstudos;
import br.unioeste.biblioteca.domain.arquivo.ArquivoBinario;
import br.unioeste.biblioteca.domain.arquivo.ArquivoEntrada;
import br.unioeste.biblioteca.domain.estruturas.Fila;
import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

public class Main {

    static final String NOME_ARQUIVO_TEXTO = "arquivo.txt";
    static final String NOME_ARQUIVO_BINARIO = "storage.data";
    //Entao dai seria locar sala. Emprestar livro. E liberar sala.
    public static void main(String[] args) {
        ArquivoEntrada arquivo = new ArquivoEntrada(NOME_ARQUIVO_TEXTO);
        ArquivoBinario storage = new ArquivoBinario(NOME_ARQUIVO_BINARIO);
        Biblioteca biblioteca = storage.obterDados();
        if (biblioteca == null) {
            biblioteca = new Biblioteca();
            biblioteca.setEstantes(arquivo.lerArquivo());
            biblioteca.setSalasEstudos(gerarSalas());
        }
        biblioteca.getEstantes().imprime(biblioteca.getEstantes());
        
        biblioteca.inserirLivroBiblioteca(biblioteca.getEstantes(), 10l, "teste", "teste", 0l, 0l);
        biblioteca.imprimirMapaEstantes(biblioteca.getEstantes());
        biblioteca.retirarLivro(biblioteca.getEstantes(), 0l);

        // Imprime os registros
        biblioteca.imprimirMapaEstantes(biblioteca.getEstantes());

        // Gera as salas
        biblioteca.imprimirSalas(biblioteca.getSalasEstudos());
        // Loca as 3 salas
        biblioteca.locarSala(biblioteca.getSalasEstudos(), 22L);
        biblioteca.locarSala(biblioteca.getSalasEstudos(), 30L);
        biblioteca.locarSala(biblioteca.getSalasEstudos(), 40L);
        biblioteca.imprimirSalas(biblioteca.getSalasEstudos());
        // Tenta locar mais uma sala, adiciona na fila de espera
        biblioteca.locarSala(biblioteca.getSalasEstudos(), 50L);
        // Imprime os registros
        biblioteca.imprimirFilaEsperaSala(biblioteca.getFilaEspera());

        // Tenta alugar um livro para um RA que não existe
        biblioteca.emprestarLivro(biblioteca.getSalasEstudos(), 56L, 1L);
        // Tenta alugar um livro que não existe
        biblioteca.emprestarLivro(biblioteca.getSalasEstudos(), 40L, 1000L);
        // Aluga um livro existente
        biblioteca.emprestarLivro(biblioteca.getSalasEstudos(), 40L, 80L);
        // Aluga um livro já alugado
        biblioteca.emprestarLivro(biblioteca.getSalasEstudos(), 40L, 80L);
        biblioteca.imprimirSalas(biblioteca.getSalasEstudos());

        // Libera sala
        biblioteca.liberarSala(biblioteca.getSalasEstudos(), 22L);
        // Aluga um livro para essa sala
        biblioteca.emprestarLivro(biblioteca.getSalasEstudos(), 50L, 80L);
        biblioteca.imprimirSalas(biblioteca.getSalasEstudos());

        // Imprime os registros
        biblioteca.imprimirFilaEsperaSala(biblioteca.getFilaEspera());
        biblioteca.imprimirSalas(biblioteca.getSalasEstudos());
        // Armazena os dados
        storage.armazenarDados(biblioteca);
        //Fila<Aluno> alunos = new Fila<Aluno>();
        //Aluno a = new Aluno();
        //a.setRa(1l);
        //Aluno a2 = new Aluno();
        //a2.setRa(22l);
        //alunos.queue(alunos, a);
        //alunos.queue(alunos, a2);
    }

    public static ListaDuplamenteEncadeada<SalaEstudos> gerarSalas() {
        ListaDuplamenteEncadeada<SalaEstudos> salas = new ListaDuplamenteEncadeada<>();
        salas.insereP(salas, new SalaEstudos(1L), 0);
        salas.insereP(salas, new SalaEstudos(2L), 1);
        salas.insereP(salas, new SalaEstudos(3L), 2);
        return salas;
    }
}
