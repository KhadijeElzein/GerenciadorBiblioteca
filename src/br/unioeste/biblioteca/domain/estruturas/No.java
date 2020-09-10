package br.unioeste.biblioteca.domain.estruturas;

import java.io.Serializable;

public class No<T> implements Serializable {

	private static final long serialVersionUID = 3580187887873837065L;

	private T info;
	private No<T> prox;
	
	public No(T info) {
		this.info = info;
		this.prox = null;
	}
	
	public T getInfo() {
		return info;
	}
	public void setInfo(T info) {
		this.info = info;
	}
	public No<T> getProx() {
		return prox;
	}
	public void setProx(No<T> prox) {
		this.prox = prox;
	}
	
	
}