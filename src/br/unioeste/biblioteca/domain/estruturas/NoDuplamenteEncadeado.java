package br.unioeste.biblioteca.domain.estruturas;

import java.io.Serializable;

public class NoDuplamenteEncadeado<T> implements Serializable {

	private static final long serialVersionUID = 4992247612530139355L;

	private T info;
	private NoDuplamenteEncadeado<T> anterior;
	private NoDuplamenteEncadeado<T> prox;
	
	public NoDuplamenteEncadeado(T info) {
		super();
		this.setInfo(info);
		this.setProx(null);
		this.setAnterior(null);
	}

	public NoDuplamenteEncadeado<T> getAnterior() {
		return anterior;
	}

	public void setAnterior(NoDuplamenteEncadeado<T> anterior) {
		this.anterior = anterior;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public NoDuplamenteEncadeado<T> getProx() {
		return prox;
	}

	public void setProx(NoDuplamenteEncadeado<T> prox) {
		this.prox = prox;
	}
	

}
