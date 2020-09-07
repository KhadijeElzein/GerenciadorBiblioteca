package br.unioeste.biblioteca.domain.estruturas;

public class Pilha<T extends Comparable<T>> {

	private No<T> topo;

	public Pilha() {
		super();
		this.topo = null;
	}

	public No<T> getTopo() {
		return topo;
	}

	public void setTopo(No<T> topo) {
		this.topo = topo;
	}

	public Boolean isVazia(Pilha<T> p) {
		return (p.getTopo() == null);
	}

	public T topo(Pilha<T> p) {
		if (!isVazia(p))
			return p.getTopo().getInfo();
		return null;
	}

	public T pop(Pilha<T> p) {
		if (!isVazia(p)) {
			T x = topo(p);
			p.setTopo(p.getTopo().getProx());
			return x;
		} else {
			System.out.println("Pilha vazia!");
			return null;
		}
	}

	public Pilha<T> push(Pilha<T> p, T item) {
		No<T> aux = new No<T>(item);
		aux.setProx(p.getTopo());
		p.setTopo(aux);
		return p;
	}

	public void imprime(Pilha<T> p) {
		if (isVazia(p))
			System.out.println("Pilha vazia");
		else {
			No<T> aux = p.getTopo();
			System.out.print("[");
			while (aux != null) {
				System.out.print(aux.getInfo() + " ");
				aux = aux.getProx();
			}
			System.out.println("]");
		}
	}

	public Pilha<T> FPVazia (Pilha<T> p){
	    p.setTopo(null);
	    return p;
	}
	public Integer getTamanho (Pilha<T> p){
	    if (isVazia(p))
	    	return 0;
	    else{
	        Integer cont =0;
	        No<T> aux = p.getTopo();
	        while (aux !=null)
	        {
	            cont++;
	            aux = aux.getProx();           
	        }
	        return cont;
	    }
	}
}
