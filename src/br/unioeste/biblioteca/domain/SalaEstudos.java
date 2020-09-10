package br.unioeste.biblioteca.domain;

import br.unioeste.biblioteca.domain.estruturas.Pilha;

import java.io.Serializable;

public class SalaEstudos implements Serializable, Comparable<SalaEstudos> {

	private static final long serialVersionUID = -225355477100862863L;

	private Long codigo;
	private Aluno aluno;
	private Pilha<Livro> livros = new Pilha<>();

	public SalaEstudos() { super(); }

	public SalaEstudos(Long codigo) {
		super();
		this.codigo = codigo;
	}

	public Long getCodigo() { return this.codigo; }
	
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	public Pilha<Livro> getLivros() {
		return livros;
	}
	public void setLivros(Pilha<Livro> livros) {
		this.livros = livros;
	}

	public Boolean estaDisponivel() {
		return this.aluno == null;
	}

	@Override
	public int compareTo(SalaEstudos o) {
		return Long.compare(this.codigo, o.codigo);
	}
}
