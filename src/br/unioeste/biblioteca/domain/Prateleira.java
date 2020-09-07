package br.unioeste.biblioteca.domain;

import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

import java.io.Serializable;

public class Prateleira implements Serializable, Comparable<Prateleira> {

	private static final long serialVersionUID = 6459924902883966181L;

	private Long codigo;
	private ListaDuplamenteEncadeada<Livro> livros;

	
	public Prateleira(Long codigo, ListaDuplamenteEncadeada<Livro> livros) {
		super();
		this.codigo = codigo;
		this.livros = livros;
	}
	
	
	public Prateleira() {
		super();
	}


	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public ListaDuplamenteEncadeada<Livro> getLivros() {
		return livros;
	}
	public void setLivros(ListaDuplamenteEncadeada<Livro> livros) {
		this.livros = livros;
	}
	@Override
	public int compareTo(Prateleira o) {
		return Long.compare(this.codigo,o.codigo);
	}

}
