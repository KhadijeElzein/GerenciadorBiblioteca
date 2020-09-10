package br.unioeste.biblioteca;

import br.unioeste.biblioteca.domain.Biblioteca;
import br.unioeste.biblioteca.domain.SalaEstudos;
import br.unioeste.biblioteca.domain.arquivo.ArquivoBinario;
import br.unioeste.biblioteca.domain.arquivo.ArquivoEntrada;
import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

import java.util.Scanner;

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

        Scanner scanIn = new Scanner(System.in);
        while (true) {
            System.out.println("------------------- MENU BIBLIOTECA -------------------");
            System.out.println("|                                                      |");
            System.out.println("| [0] -> Sair                                          |");
            System.out.println("| [1] -> Carregar Arquivo Texto                        |");
            System.out.println("| [2] -> Inserir Livro                                 |");
            System.out.println("| [3] -> Retirar Livro                                 |");
            System.out.println("| [4] -> Buscar Livro                                  |");
            System.out.println("| [5] -> Locar Sala                                    |");
            System.out.println("| [6] -> Emprestar Livro                               |");
            System.out.println("| [7] -> Liberar Sala                                  |");
            System.out.println("| [8] -> Imprimir Mapa de Estantes                     |");
            System.out.println("| [9] -> Imprimir Mapa de Salas                        |");
            System.out.println("| [10] -> Imprimir Fila de Espera de Sala              |");
            System.out.println("--------------------------------------------------------");

            String option;

            option = scanIn.nextLine();

            // Se por algum motivo não carregou nenhum dado e a opção não for a de carregar arquivo...
            if (biblioteca == null && !"1".equals(option)) {
                System.out.println("Nenhum dado carregado...");
                continue;
            }

            if ("0".equals(option)) {
                break;
            } else if ("1".equals(option)) {
                biblioteca = new Biblioteca();
                biblioteca.setEstantes(arquivo.lerArquivo());
                biblioteca.setSalasEstudos(gerarSalas());
                System.out.println("Arquivo texto carregado...");
            } else if ("2".equals(option)) {
                System.out.println("------------------- INSERIR LIVRO----------------------");
                System.out.println("| Informe o codigo do livro...                         |");
                String codivoLivro = scanIn.nextLine();
                System.out.println("| Informe o titulo do livro...                         |");
                String tituloLivro = scanIn.nextLine();
                System.out.println("| Informe o autor do livro...                          |");
                String autorLivro = scanIn.nextLine();
                System.out.println("| Informe a estante...                                 |");
                String estante = scanIn.nextLine();
                System.out.println("| Informe a prateleira...                              |");
                String prateleira = scanIn.nextLine();
                biblioteca.inserirLivroBiblioteca(biblioteca.getEstantes(), Long.valueOf(codivoLivro), tituloLivro,
                        autorLivro, Long.valueOf(estante), Long.valueOf(prateleira));
            } else if ("3".equals(option)) {
                System.out.println("------------------- RETIRAR LIVRO----------------------");
                System.out.println("| Informe o codigo do livro...                         |");
                String codivoLivro = scanIn.nextLine();
                biblioteca.retirarLivro(biblioteca.getEstantes(), Long.valueOf(codivoLivro));
            } else if ("4".equals(option)) {
                System.out.println("------------------- BUSCAR LIVRO ----------------------");
                System.out.println("| Informe o codigo do livro...                         |");
                String codivoLivro = scanIn.nextLine();
                biblioteca.buscaEnderecoLivro(biblioteca.getEstantes(), Long.valueOf(codivoLivro));
            } else if ("5".equals(option)) {
                System.out.println("------------------- LOCAR SALA ------------------------");
                System.out.println("| Informe o RA...                                     |");
                String ra = scanIn.nextLine();
                biblioteca.locarSala(biblioteca.getSalasEstudos(), Long.valueOf(ra));
            } else if ("6".equals(option)) {
                System.out.println("------------------- LOCAR LIVRO -----------------------");
                System.out.println("| Informe o RA...                                     |");
                String ra = scanIn.nextLine();
                System.out.println("| Informe o codigo do livro...                        |");
                String codigoLivro = scanIn.nextLine();
                biblioteca.emprestarLivro(biblioteca.getSalasEstudos(), Long.valueOf(ra), Long.valueOf(codigoLivro));
            } else if ("7".equals(option)) {
                System.out.println("------------------- LIBERAR SALA ----------------------");
                System.out.println("| Informe o RA...                                     |");
                String ra = scanIn.nextLine();
                biblioteca.liberarSala(biblioteca.getSalasEstudos(), Long.valueOf(ra));
            } else if ("8".equals(option)) {
                biblioteca.imprimirMapaEstantes(biblioteca.getEstantes());
            } else if ("9".equals(option)) {
                biblioteca.imprimirSalas(biblioteca.getSalasEstudos());
            } else if ("10".equals(option)) {
                biblioteca.imprimirFilaEsperaSala(biblioteca.getFilaEspera());
            }
        }

        scanIn.close();

        // Armazena os dados
        storage.armazenarDados(biblioteca);
    }

    public static ListaDuplamenteEncadeada<SalaEstudos> gerarSalas() {
        ListaDuplamenteEncadeada<SalaEstudos> salas = new ListaDuplamenteEncadeada<>();
        salas.insereP(salas, new SalaEstudos(1L), 0);
        salas.insereP(salas, new SalaEstudos(2L), 1);
        salas.insereP(salas, new SalaEstudos(3L), 2);
        return salas;
    }
}
