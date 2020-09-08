package br.unioeste.biblioteca.domain;

import br.unioeste.biblioteca.domain.estruturas.*;
import br.unioeste.biblioteca.service.interfaces.BibliotecaService;

import java.io.Serializable;

public class Biblioteca implements BibliotecaService, Serializable {

	private static final long serialVersionUID = 7030740307833658544L;

	private ListaDuplamenteEncadeada<Estante> estantes;

	private Fila<Aluno> filaEspera = new Fila<>();

	ListaDuplamenteEncadeada<SalaEstudos> salasEstudos;

	public ListaDuplamenteEncadeada<Estante> getEstantes() {
		return estantes;
	}

	public void setEstantes(ListaDuplamenteEncadeada<Estante> estantes) {
		this.estantes = estantes;
	}

	public Fila<Aluno> getFilaEspera() {
		return this.filaEspera;
	}

	public ListaDuplamenteEncadeada<SalaEstudos> getSalasEstudos() {
		return salasEstudos;
	}

	public void setSalasEstudos(ListaDuplamenteEncadeada<SalaEstudos> salasEstudos) {
		this.salasEstudos = salasEstudos;
	}

	private ListaDuplamenteEncadeada<Estante> inserirEstante(ListaDuplamenteEncadeada<Estante> estantes, Long codigo) {
		ListaDuplamenteEncadeada<Prateleira> prateleiras = new ListaDuplamenteEncadeada<Prateleira>();
		Estante nova = new Estante(codigo, prateleiras);
		estantes = estantes.insereInicio(estantes, nova);
		estantes = estantes.ordenar(estantes);
		return estantes;
	}

	/* insere livro na cabeça e ordena pelo código */
	private ListaDuplamenteEncadeada<Livro> inserirLivro(ListaDuplamenteEncadeada<Livro> livros, Long codigo,
			String titulo, String autor, Long codEstante, Long codPrateleira) {
		Livro novo = new Livro(codigo, titulo, autor, codEstante, codPrateleira, false);
	    livros = livros.insereInicio(livros, novo);
		livros = livros.ordenar(livros);
		return livros;
	}

	private ListaDuplamenteEncadeada<Prateleira> inserirPrateleira(ListaDuplamenteEncadeada<Prateleira> prateleiras,
			Long codigo) {
		ListaDuplamenteEncadeada<Livro> livros = new ListaDuplamenteEncadeada<Livro>();
		Prateleira nova = new Prateleira(codigo, livros);
		prateleiras = prateleiras.insereInicio(prateleiras, nova);
		prateleiras = prateleiras.ordenar(prateleiras);
		return prateleiras;
	}

	@Override
	public ListaDuplamenteEncadeada<Estante> inserirLivroBiblioteca(ListaDuplamenteEncadeada<Estante> estantes,
			Long codigo, String titulo, String autor, Long codEstante, Long codPrateleira) {
		if (estantes!=null && !estantes.vazia(estantes)) {
			NoDuplamenteEncadeado<Estante> auxEstante = estantes.getPrimeiro();
			while (auxEstante != null && !auxEstante.getInfo().getCodigo().equals(codEstante))
				auxEstante = auxEstante.getProx();
			if (auxEstante != null) {
				ListaDuplamenteEncadeada<Prateleira> prateleiras = auxEstante.getInfo().getPrateleiras();
				if (prateleiras!=null && !prateleiras.vazia(prateleiras)) {
					NoDuplamenteEncadeado<Prateleira> auxPrateleira = prateleiras.getPrimeiro();
					while (auxPrateleira != null && !auxPrateleira.getInfo().getCodigo().equals(codPrateleira))
						auxPrateleira = auxPrateleira.getProx();
					if (auxPrateleira != null) {
						ListaDuplamenteEncadeada<Livro> livros = auxPrateleira.getInfo().getLivros();
						NoDuplamenteEncadeado<Livro> auxLivro = livros.getPrimeiro();
						while (auxLivro != null && !auxLivro.getInfo().getCodigo().equals(codigo)) {
							auxLivro = auxLivro.getProx();
						}
						if (auxLivro != null) {
							System.out.println("Livro com código já existente, impossível inserir");
						}
						else { 
						livros = inserirLivro(livros, codigo, titulo, autor, codEstante, codPrateleira);
						System.out.println("O livro com código " + codigo + "foi inserido na estante " + codEstante
								+ "e prateleira " + codPrateleira);
						}
					} else {
						prateleiras = inserirPrateleira(prateleiras, codPrateleira);
						estantes = inserirLivroBiblioteca(estantes, codigo, titulo, autor, codEstante, codPrateleira);
					}
				} else {
					prateleiras = inserirPrateleira(prateleiras, codPrateleira);
					estantes = inserirLivroBiblioteca(estantes, codigo, titulo, autor, codEstante, codPrateleira);
				}
			} else {
				estantes = inserirEstante(estantes, codEstante);
				estantes = inserirLivroBiblioteca(estantes, codigo, titulo, autor, codEstante, codPrateleira);
			}
		} else {
			estantes = inserirEstante(estantes, codEstante);
			estantes = inserirLivroBiblioteca(estantes, codigo, titulo, autor, codEstante, codPrateleira);
		}
		return estantes;
	}

	@Override
	public ListaDuplamenteEncadeada<Estante> retirarLivro(ListaDuplamenteEncadeada<Estante> estantes, Long codLivro) {
		if (estantes!=null && !estantes.vazia(estantes)) {
			NoDuplamenteEncadeado<Estante> auxEstante = estantes.getPrimeiro();
			while (auxEstante != null) {
				ListaDuplamenteEncadeada<Prateleira> prateleiras = auxEstante.getInfo().getPrateleiras();
				if (prateleiras!=null && !prateleiras.vazia(prateleiras)) {
					NoDuplamenteEncadeado<Prateleira> auxPrateleira = prateleiras.getPrimeiro();

					while (auxPrateleira != null) {
						ListaDuplamenteEncadeada<Livro> livros = auxPrateleira.getInfo().getLivros();
						if (livros!=null && !livros.vazia(livros)) {
							NoDuplamenteEncadeado<Livro> auxLivro = livros.getPrimeiro();
							while (auxLivro != null && !auxLivro.getInfo().getCodigo().equals(codLivro))
								auxLivro = auxLivro.getProx();
							if (auxLivro != null) {
								livros = livros.remover(livros, auxLivro.getInfo());
								auxPrateleira.getInfo().setLivros(livros);
								System.out.println("Livro de código " + codLivro + " removido.");
								return estantes;
							}
						}
						auxPrateleira = auxPrateleira.getProx();
					}
				}
				auxEstante = auxEstante.getProx();
			}
			System.out.println("Nada para remover.");
			return estantes;
		} else {
			System.out.println("Nada para remover.");
			return estantes;
		}
	}

	@Override
	public void buscaEnderecoLivro(ListaDuplamenteEncadeada<Estante> estantes, Long codLivro){
	    if (estantes != null && !estantes.vazia(estantes)) {
	    	Livro livro = buscarLivro(estantes, codLivro);

	    	if (livro == null) {
				System.out.println("Livro indisponível (não cadastrado na biblioteca)");
			} else if (livro.getIsEmprestado()) {
				System.out.println("Livro indisponível (emprestado)");
			} else {
				System.out.println("Este livro encontra-se na Estante " +
						livro.getCodEstante()  + ", Prateleira " +
						livro.getCodPrateleira());
			}
	   } else {
			System.out.println("Livro indisponível (não cadastrado na biblioteca)");
		}
	}
	
	@Override
	public void imprimirMapaEstantes(ListaDuplamenteEncadeada<Estante> estantes) {
	
		if (estantes!= null && !estantes.vazia(estantes)) {
			NoDuplamenteEncadeado<Estante> auxEstante = estantes.getPrimeiro();
			while (auxEstante != null) {
				System.out.println("==============================================================================");
				System.out.println(
						"|                                 Estante " + auxEstante.getInfo().getCodigo() + "         |");
				ListaDuplamenteEncadeada<Prateleira> prateleiras = auxEstante.getInfo().getPrateleiras();
				if (prateleiras!= null && !prateleiras.vazia(prateleiras)) {
					NoDuplamenteEncadeado<Prateleira> auxPrateleira = prateleiras.getPrimeiro();
	
					while (auxPrateleira != null) {
						System.out.println(
								"|       _______________________________________________________________      |");
						System.out.println(
								"|                         Prateleira " + auxPrateleira.getInfo().getCodigo() + "  | ");
						System.out.println(
								"|      ______________________________________________________________        | ");
						ListaDuplamenteEncadeada<Livro> livros = auxPrateleira.getInfo().getLivros();
						if (livros!= null && !livros.vazia(livros)) {
							NoDuplamenteEncadeado<Livro> auxLivro = livros.getPrimeiro();
							while (auxLivro != null && !auxLivro.getInfo().getIsEmprestado()) {
								System.out.println(
										"|  " + auxLivro.getInfo().getCodigo() + ", " + auxLivro.getInfo().getAutor()
												+ ", " + auxLivro.getInfo().getTitulo() + "    |");
								auxLivro = auxLivro.getProx();
							}
						}
						auxPrateleira = auxPrateleira.getProx();
					}
				}
				auxEstante = auxEstante.getProx();
			}
		}
	}
	
	@Override
	public void imprimirFilaEsperaSala(Fila<Aluno> filaEspera) {
		No<Aluno> aux = filaEspera.getInicio();
		System.out.println("----- FILA DE ESPERA DAS SALAS DE ESTUDOS -----");
		while (aux != null) {
			System.out.println("RA DO ALUNO = " + aux.getInfo().getRa());
			aux = aux.getProx();
		}
	}

	@Override
	public ListaDuplamenteEncadeada<SalaEstudos> locarSala(ListaDuplamenteEncadeada<SalaEstudos> salasEstudos, Long ra) {
		Aluno aluno = new Aluno();
		aluno.setRa(ra);
		if (salasEstudos != null && !salasEstudos.vazia(salasEstudos)) {
			boolean encontrouSalaLivre = false;
			NoDuplamenteEncadeado<SalaEstudos> aux = salasEstudos.getPrimeiro();
			while (aux != null) {
				if (aux.getInfo().estaDisponivel()) {
					aux.getInfo().setAluno(aluno);
					encontrouSalaLivre = true;
					break;
				}
				aux = aux.getProx();
			}
			if (!encontrouSalaLivre) {
				filaEspera.queue(filaEspera, aluno);
			}
		}
		return salasEstudos;
	}

	@Override
	public ListaDuplamenteEncadeada<SalaEstudos> liberarSala(ListaDuplamenteEncadeada<SalaEstudos> salasEstudos, Long ra) {
		Aluno aluno = new Aluno();
		aluno.setRa(ra);
		if (salasEstudos != null && !salasEstudos.vazia(salasEstudos)) {
			NoDuplamenteEncadeado<SalaEstudos> aux = salasEstudos.getPrimeiro();
			while (aux != null) {
				// Encontrou a sala, deixa-a disponivel e loca para algum aluno na fila de espera
				if (!aux.getInfo().estaDisponivel() && aux.getInfo().getAluno().getRa().equals(ra)) {
					// Remove o aluno
					aux.getInfo().setAluno(null);
					// Devolve os livros
					if (aux.getInfo().getLivros() != null && !aux.getInfo().getLivros().isVazia(aux.getInfo().getLivros())) {
						Livro livro = aux.getInfo().getLivros().pop(aux.getInfo().getLivros());
						while (livro != null) {
							livro.setIsEmprestado(false);
							livro = aux.getInfo().getLivros().pop(aux.getInfo().getLivros());
						}
						System.out.println("Sala " + aux.getInfo().getCodigo() + " liberada.");
					}
					// Chama o aluno na fila de espera
					if (!filaEspera.isVazia(filaEspera)) {
						Aluno alunoDaFila = filaEspera.dequeue(filaEspera);
						this.locarSala(salasEstudos, alunoDaFila.getRa());
					}
					break;
				}
				aux = aux.getProx();
			}
		}
		return salasEstudos;
	}

	@Override
	public void imprimirSalas(ListaDuplamenteEncadeada<SalaEstudos> salas) {
		if (salas != null && !salas.vazia(salas)) {
			NoDuplamenteEncadeado<SalaEstudos> auxSala = salas.getPrimeiro();
			while (auxSala != null) {
				System.out.println("==============================================================================");
				System.out.println(
						"|                                 Sala " + auxSala.getInfo().getCodigo() + "         |");
				if (auxSala.getInfo().estaDisponivel()) {
					System.out.println("|                                 Vazia          |");
				} else {
					System.out.println("|                                 RA: " + auxSala.getInfo().getAluno().getRa() + "         |");
					Pilha livros = auxSala.getInfo().getLivros();
					if (livros != null && !livros.isVazia(livros)) {
						No<Livro> auxLivro = livros.getTopo();
						while (auxLivro != null) {
							System.out.println(
									"|  " + auxLivro.getInfo().getCodigo() + ", " + auxLivro.getInfo().getAutor()
											+ ", " + auxLivro.getInfo().getTitulo() + "    |");
							auxLivro = auxLivro.getProx();
						}
					}
				}
				auxSala = auxSala.getProx();
			}
		}
	}

	@Override
	public void emprestarLivro(ListaDuplamenteEncadeada<SalaEstudos> salas, Long ra, Long codigoLivro) {
		if (salas != null && !salas.vazia(salas)) {
			NoDuplamenteEncadeado<SalaEstudos> auxSala = salas.getPrimeiro();
			SalaEstudos salaEncontrada = null;
			while (auxSala != null) {
				if (!auxSala.getInfo().estaDisponivel() && auxSala.getInfo().getAluno().getRa().equals(ra)) {
					salaEncontrada = auxSala.getInfo();
					break;
				}
				auxSala = auxSala.getProx();
			}
			if (salaEncontrada == null) {
				System.out.println("Sala não encontrada para o RA: " + ra);
			} else {
				Livro livro = buscarLivro(getEstantes(), codigoLivro);
				if (livro == null) {
					System.out.println("Livro indisponível (não cadastrado na biblioteca)");
				} else if (livro.getIsEmprestado()) {
					System.out.println("Livro indisponível (emprestado)");
				} else {
					System.out.println("Livro emprestado com sucesso!");
					livro.setIsEmprestado(true);
					salaEncontrada.getLivros().push(salaEncontrada.getLivros(), livro);
				}
			}
		}
	}

	private Livro buscarLivro(ListaDuplamenteEncadeada<Estante> estantes, Long codLivro) {
		if (estantes != null && !estantes.vazia(estantes)) {
			NoDuplamenteEncadeado<Estante> auxEstante = estantes.getPrimeiro();
			while (auxEstante != null) {
				ListaDuplamenteEncadeada<Prateleira> prateleiras = auxEstante.getInfo().getPrateleiras();
				if (prateleiras != null && !prateleiras.vazia(prateleiras)) {
					NoDuplamenteEncadeado<Prateleira> auxPrateleira = prateleiras.getPrimeiro();
					while (auxPrateleira != null) {
						ListaDuplamenteEncadeada<Livro> livros = auxPrateleira.getInfo().getLivros();
						if (livros != null && !livros.vazia(livros)) {
							NoDuplamenteEncadeado<Livro> auxLivro = livros.getPrimeiro();
							while (auxLivro != null && !auxLivro.getInfo().getCodigo().equals(codLivro)) {
								auxLivro = auxLivro.getProx();
							}
							if (auxLivro != null) {
								return auxLivro.getInfo();
							}

						}
						auxPrateleira = auxPrateleira.getProx();
					}
				}
				auxEstante = auxEstante.getProx();
			}
		}
		return null;
	}
}
