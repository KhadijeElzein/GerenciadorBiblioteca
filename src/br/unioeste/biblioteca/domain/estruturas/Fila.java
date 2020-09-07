package br.unioeste.biblioteca.domain.estruturas;

public class Fila<T> {
	private No<T> inicio;
	private No<T> fim;
	
	public No<T> getInicio() {
		return inicio;
	}
	public void setInicio(No<T> inicio) {
		this.inicio = inicio;
	}
	public No<T> getFim() {
		return fim;
	}
	public void setFim(No<T> fim) {
		this.fim = fim;
	}
	
	public Fila() {
		super();
		this.inicio = null;
		this.fim = null;
	}
	
	public Boolean isVazia(Fila<T> f){
	    return (f.getInicio() == null);
	}

	public Fila<T> queue(Fila<T> f, T x){
	    No<T> aux = new No<T>(x);
	    aux.setProx(null);
	    if (isVazia(f))
	    	f.setInicio(aux);            
	    else   
	    	f.getFim().setProx(aux);
	    f.setFim(aux);
	    return f;
	}

	public T dequeue (Fila<T> f){
	   T item = f.getInicio().getInfo();

	    if (f.getInicio() == f.getFim())
	        f.setFim(null);
	    f.setInicio(f.getInicio().getProx());
	    return item;
	}
	
	public void imprime(Fila<T> f){
	    if(isVazia(f))
	        System.out.println("Fila vazia");
	    else{
	    	No<T> aux = f.getInicio();
			System.out.print("[");
			while (aux != null) {
				System.out.print(aux.getInfo() + " ");
				aux = aux.getProx();
			}
			System.out.println("]");
	    }
	}

	public Fila<T> FFVazia (Fila<T> f){
	    f.setInicio(null); 
	    f.setFim(null);
	    return f;
	}
}
