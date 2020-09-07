package br.unioeste.biblioteca.domain;

import java.io.Serializable;

public class Aluno implements Serializable {

	private static final long serialVersionUID = 8270835138138550626L;

	private Long ra;

	public Long getRa() {
		return ra;
	}

	public void setRa(Long ra) {
		this.ra = ra;
	}
	
}
