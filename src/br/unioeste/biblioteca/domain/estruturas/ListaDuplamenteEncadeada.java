package br.unioeste.biblioteca.domain.estruturas;

import java.io.Serializable;

public class ListaDuplamenteEncadeada<T extends Comparable<T>> implements Serializable {

	private static final long serialVersionUID = 7146424347680198315L;

	private NoDuplamenteEncadeado<T> primeiro;
	
	private NoDuplamenteEncadeado<T> ultimo;
	
	public ListaDuplamenteEncadeada() {
		super();
		this.primeiro = null;
		this.ultimo = null;
	}

	public NoDuplamenteEncadeado<T> getPrimeiro() {
		return primeiro;
	}

	public void setPrimeiro(NoDuplamenteEncadeado<T> primeiro) {
		this.primeiro = primeiro;
	}

	public NoDuplamenteEncadeado<T> getUltimo() {
		return ultimo;
	}

	public void setUltimo(NoDuplamenteEncadeado<T> ultimo) {
		this.ultimo = ultimo;
	}
	
	
	public ListaDuplamenteEncadeada<T> insereInicio(ListaDuplamenteEncadeada<T> l, T x) {
		NoDuplamenteEncadeado<T> elemento = new NoDuplamenteEncadeado<T>(x);
		elemento.setAnterior(null);
		elemento.setProx(l.getPrimeiro());
		if (vazia(l)) {
			l.setUltimo(elemento);
		} else {
			l.getPrimeiro().setAnterior(elemento);
		}
		l.setPrimeiro(elemento);
		return l;
	}
	
	public ListaDuplamenteEncadeada<T> remover(ListaDuplamenteEncadeada<T> l, T x) {
		if (!vazia(l)) {
			NoDuplamenteEncadeado<T> p = l.getPrimeiro();
			while(p != null && p.getInfo() != x) {
			    p = p.getProx();
			    		
			}
		    if(p != null) {
		    	if(p != l.getPrimeiro()) {
		    	 p.getAnterior().setProx(p.getProx());
		    	} else {
		    		l.setPrimeiro(p.getProx());
		    	}
		    	if(p != l.getUltimo()) {
			 		p.getProx().setAnterior(p.getAnterior());
		    	} else {
					l.setUltimo(p.getAnterior());
		    	}
		    }else System.out.println("Elemento não encontrado");
		}else System.out.println("Lista vazia");
		return l;
	}

	public Integer busca(ListaDuplamenteEncadeada<T> l, T x) {
		Integer posicao = 0;
		if (!vazia(l)) {
			NoDuplamenteEncadeado<T> p = l.getPrimeiro();
			while(p != null && p.getInfo() != x) {
			    p = p.getProx();
				posicao++;
			}
			if (p != null) {
				return posicao;
			} 	
		}
		return null;
	 }

	
	public ListaDuplamenteEncadeada<T> FLVazia(ListaDuplamenteEncadeada<T> l) {
		NoDuplamenteEncadeado<T> aux = l.getPrimeiro();
		while(aux!=null) {
			aux.setInfo(null);
			aux.setAnterior(null);
			aux = aux.getProx();
		}
		l.getPrimeiro().setAnterior(null);
		l.setPrimeiro(null);
		l.getUltimo().setAnterior(null);
		l.getUltimo().setProx(null);
		l.setUltimo(null);
		return l;
	}
	
	public ListaDuplamenteEncadeada<T> insereFim(ListaDuplamenteEncadeada<T> l, T x) {
		NoDuplamenteEncadeado<T> elemento = new NoDuplamenteEncadeado<T>(x);
		elemento.setProx(null);
		elemento.setAnterior(l.getUltimo());
		if (vazia(l)) {
			l.setPrimeiro(elemento);
		} else {
			l.getUltimo().setProx(elemento);
		}
		l.setUltimo(elemento);
		return l;
	}

	public ListaDuplamenteEncadeada<T> insereP(ListaDuplamenteEncadeada<T> l, T x, Integer p) {
		NoDuplamenteEncadeado<T> elemento = new NoDuplamenteEncadeado<T>(x);
		if (vazia(l)) {
			l.setPrimeiro(elemento);
			l.setUltimo(elemento);
			l.getPrimeiro().setAnterior(null);  
			l.getUltimo().setProx(null);
		} else {
			if (p == 0)
				insereInicio(l, x);
			else {
				NoDuplamenteEncadeado<T> aux = l.getPrimeiro();
				for (int i = 0; i < p - 1; i++) {
					aux = aux.getProx();
				
				}
				if(aux == null) System.out.println("posição acima do tamanho da lista");
				else {
					NoDuplamenteEncadeado<T> store = aux.getProx();
					if(store==null)
						insereFim(l,x);
					else {
						store.setAnterior(aux);
						aux.setProx(elemento);
						elemento.setAnterior(aux);
						elemento.setProx(store);
						store.setAnterior(elemento);
					}
				}
			}
		}
		return l;
	}

	
	public T retira(Integer p, ListaDuplamenteEncadeada<T> l, T x) {
		NoDuplamenteEncadeado<T> aux = l.getPrimeiro();
		for (int i = 0; i < p; i++) {
			aux = aux.getProx();
		}
		if (aux.getInfo().equals(x)) {
			if(aux.equals(l.getPrimeiro())) {
				l.setPrimeiro(aux.getProx());
			}else  aux.getAnterior().setProx(aux.getProx());
		}
		return x;
	}

	
	public Boolean vazia(ListaDuplamenteEncadeada<T> l) {
		return (l.getPrimeiro() == null);
	}

	
	public void imprime(ListaDuplamenteEncadeada<T> l) {
		if (vazia(l))
			System.out.println("Lista vazia");
		else {
			NoDuplamenteEncadeado<T> aux = l.getPrimeiro();
			System.out.print("[");
			while (aux != null) {
				System.out.print(aux.getInfo()+ " ");
				aux = aux.getProx();
			}
			System.out.println("]");
		}
	}

	
	public ListaDuplamenteEncadeada<T> ordenar(ListaDuplamenteEncadeada<T> l) {
		NoDuplamenteEncadeado<T> atual = l.getPrimeiro();
		while (atual != null) {
			NoDuplamenteEncadeado<T> prox = atual.getProx();
			while (prox != null) {
				if (atual.getInfo().compareTo(prox.getInfo()) > 0) {
					T tmp = atual.getInfo();
					atual.setInfo(prox.getInfo());
					prox.setInfo(tmp);
				}
				prox = prox.getProx();
			}
			atual = atual.getProx();
		}
		return l;
	}

	
	public ListaDuplamenteEncadeada<T> concatenar(ListaDuplamenteEncadeada<T> l1, ListaDuplamenteEncadeada<T> l2) {
		 NoDuplamenteEncadeado<T> atual1 = l1.getPrimeiro();
		 NoDuplamenteEncadeado<T> atual2 = l2.getPrimeiro();
		 ListaDuplamenteEncadeada<T> l3 = new ListaDuplamenteEncadeada<T>();
         while (atual1 != null) 
         {
        	 insereFim(l3,atual1.getInfo());
        	 atual1 = atual1.getProx();
         }
         while (atual2 != null) 
         {
        	 insereFim(l3,atual2.getInfo());
        	 atual2 = atual2.getProx();
         }
         return l3;
	}

}
