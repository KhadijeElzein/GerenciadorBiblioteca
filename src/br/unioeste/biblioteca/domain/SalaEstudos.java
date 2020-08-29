package br.unioeste.biblioteca.domain;

import br.unioeste.biblioteca.domain.estruturas.Pilha;

public class SalaEstudos {
	private Aluno aluno;
	private Pilha<Livro> livros;
	
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
}
