package br.unioeste.biblioteca.domain.arquivo;

import br.unioeste.biblioteca.domain.Estante;
import br.unioeste.biblioteca.domain.Livro;
import br.unioeste.biblioteca.domain.Prateleira;
import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ArquivoEntrada {
    private String nomeArquivo;

    private Estante estanteAtual;
    private Prateleira prateleiraAtual;

    public ArquivoEntrada(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public ListaDuplamenteEncadeada<Estante> lerArquivo() {
        ListaDuplamenteEncadeada<Estante> listaDuplamenteEncadeada = new ListaDuplamenteEncadeada<>();
        try {
            File myObj = new File(this.nomeArquivo);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                listaDuplamenteEncadeada = this.parse(listaDuplamenteEncadeada, data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return listaDuplamenteEncadeada;
    }

    private ListaDuplamenteEncadeada<Estante> parse(ListaDuplamenteEncadeada<Estante> lista, String linha) {
        if (linha.startsWith("E")) {
            Long codigoEstante = Long.valueOf(linha.substring(1));
            this.estanteAtual = new Estante();
            estanteAtual.setCodigo(codigoEstante);
            lista = lista.insereP(lista, estanteAtual, codigoEstante.intValue());
        } else if (linha.startsWith("P")) {
            Long codigoPrateleira = Long.valueOf(linha.substring(1));
            this.prateleiraAtual = new Prateleira();
            prateleiraAtual.setCodigo(codigoPrateleira);
            if (this.estanteAtual != null) {
                if (this.estanteAtual.getPrateleiras() == null) {
                    this.estanteAtual.setPrateleiras(new ListaDuplamenteEncadeada<>());
                }
                this.estanteAtual.setPrateleiras(this.estanteAtual.getPrateleiras().insereP(this.estanteAtual.getPrateleiras(), this.prateleiraAtual, codigoPrateleira.intValue()));
            }
        } else {
            String[] livroParts = linha.split(",");
            if (livroParts != null && livroParts.length > 0) {
                Long codigoLivro = Long.valueOf(livroParts[0]);
                String titulo = livroParts[1];
                String autor = livroParts[2];
                Livro livro = new Livro();
                livro.setCodigo(codigoLivro);
                livro.setTitulo(titulo);
                livro.setAutor(autor);
                livro.setCodPrateleira(this.prateleiraAtual.getCodigo());
                livro.setCodEstante(this.estanteAtual.getCodigo());
                if (this.prateleiraAtual.getLivros() == null) {
                    this.prateleiraAtual.setLivros(new ListaDuplamenteEncadeada<>());
                }
                int indiceLivro = 0;
                if (this.prateleiraAtual.getLivros().getUltimo() != null) {
                    indiceLivro = this.prateleiraAtual.getLivros().busca(this.prateleiraAtual.getLivros(),
                            this.prateleiraAtual.getLivros().getUltimo().getInfo());
                    indiceLivro++;
                }
                this.prateleiraAtual.setLivros(this.prateleiraAtual.getLivros().insereP(this.prateleiraAtual.getLivros(),
                        livro, indiceLivro));
            }
        }
        return lista;
    }
}
