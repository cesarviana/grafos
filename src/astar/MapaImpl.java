package astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MapaImpl implements Mapa {

	private Posicao inicio;
	private Posicao fim;
	private int linhas;
	private int colunas;
	private Collection<Posicao> muro;

	public MapaImpl(Posicao inicio, Posicao fim, int linhas, int colunas,
			Collection<Posicao> muro) {
		super();
		this.inicio = inicio;
		this.fim = fim;
		this.linhas = linhas;
		this.colunas = colunas;
		this.muro = muro;
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	@Override
	public Posicao getInicio() {
		return inicio;
	}

	@Override
	public Posicao getFim() {
		return fim;
	}

	@Override
	public Collection<Posicao> getVizinhosAcessiveisDe(Posicao posicao) {

		Collection<Posicao> vizinhos = criaListaDeVizinhos(posicao);
		marcaMuros(vizinhos);
		removeInacessiveis(vizinhos);

		return vizinhos;
	}

	private ArrayList<Posicao> criaListaDeVizinhos(Posicao posicao) {
		return new ArrayList<>(Arrays.asList(posicao.cima(),
				posicao.cimaDireita(), posicao.direita(),
				posicao.baixoDireita(), posicao.baixo(),
				posicao.baixoEsquerda(), posicao.esquerda(),
				posicao.cimaEsquerda()));
	}

	private void marcaMuros(Collection<Posicao> vizinhos) {
		vizinhos.forEach(vizinho -> {
			boolean visinhoIsMuro = muro.stream().anyMatch(
					posicaoMuro -> posicaoMuro.coluna == vizinho.coluna
							&& posicaoMuro.linha == vizinho.linha);
			if (visinhoIsMuro) {
				vizinho.tipo = Posicao.Tipo.MURO;
			}
		});
	}

	private void removeInacessiveis(Collection<Posicao> vizinhos) {
		vizinhos.removeIf(vizinho -> vizinho.coluna == 0 || vizinho.linha == 0
				|| vizinho.coluna > colunas || vizinho.linha > linhas
				|| !vizinho.isAcessivel());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(linhas).append(" linhas").append(",")
		.append(colunas).append(" colunas");
		return sb.toString();
	}
}
