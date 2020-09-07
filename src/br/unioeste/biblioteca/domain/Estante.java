package br.unioeste.biblioteca.domain;

import br.unioeste.biblioteca.domain.estruturas.ListaDuplamenteEncadeada;

public class Estante implements Comparable<Estante> {
	private Long codigo;
	private ListaDuplamenteEncadeada<Prateleira> prateleiras;
	
	
	public Estante() {
		super();
	}
	public Estante(Long codigo, ListaDuplamenteEncadeada<Prateleira> prateleiras) {
		super();
		this.codigo = codigo;
		this.prateleiras = prateleiras;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public ListaDuplamenteEncadeada<Prateleira> getPrateleiras() {
		return prateleiras;
	}
	public void setPrateleiras(ListaDuplamenteEncadeada<Prateleira> prateleiras) {
		this.prateleiras = prateleiras;
	}
	@Override
	public int compareTo(Estante o) {
		return Long.compare(this.codigo,o.codigo);
	}
}
