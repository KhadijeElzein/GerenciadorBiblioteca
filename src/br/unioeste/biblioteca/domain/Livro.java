package br.unioeste.biblioteca.domain;

public class Livro implements Comparable<Livro>{
	private Long codigo;						
	private String titulo;
	private String autor; 
	private Long codEstante;
	private Long codPrateleira;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Long getCodEstante() {
		return codEstante;
	}
	public void setCodEstante(Long codEstante) {
		this.codEstante = codEstante;
	}
	public Long getCodPrateleira() {
		return codPrateleira;
	}
	public void setCodPrateleira(Long codPrateleira) {
		this.codPrateleira = codPrateleira;
	}
	@Override
	public int compareTo(Livro o) {
		// TODO Auto-generated method stub
		return Long.compare(this.codigo,o.codigo);
	}
	
	
	
}
