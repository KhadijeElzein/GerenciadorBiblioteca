package br.unioeste.biblioteca.service.interfaces;

import br.unioeste.biblioteca.domain.Aluno;
import br.unioeste.biblioteca.domain.Estante;
import br.unioeste.biblioteca.domain.SalaEstudos;
import br.unioeste.biblioteca.domain.estruturas.Fila;
import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

public interface BibliotecaService {
	
	public ListaDuplamenteEncadeada<Estante> inserirLivroBiblioteca(ListaDuplamenteEncadeada<Estante> estantes,
			Long codigo, String titulo, String autor, Long codEstante, Long codPrateleira); 
	
	public ListaDuplamenteEncadeada<Estante> retirarLivro(ListaDuplamenteEncadeada<Estante> estantes, Long codLivro);
	
	public void buscaEnderecoLivro(ListaDuplamenteEncadeada<Estante> estantes, Long codLivro);
	
	public void imprimirMapaEstantes(ListaDuplamenteEncadeada<Estante> estantes);
	
	public void imprimirFilaEsperaSala();

	public ListaDuplamenteEncadeada<SalaEstudos> locarSala(ListaDuplamenteEncadeada<SalaEstudos> salasEstudos, Long ra);

	public ListaDuplamenteEncadeada<SalaEstudos> liberarSala(ListaDuplamenteEncadeada<SalaEstudos> salasEstudos, Long ra);
}
