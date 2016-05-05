package astar;

import java.util.ArrayList;
import java.util.Collection;

public class MapaBuilder {

	private Posicao inicio;
	private Posicao fim;
	private int linhas;
	private int colunas;
	private final Collection<Posicao> muro = new ArrayList<>();

	public MapaBuilder setLinhas(int linhas) {
		this.linhas = linhas;
		return this;
	}

	public MapaBuilder setColunas(int colunas) {
		this.colunas = colunas;
		return this;
	}

	public MapaBuilder setInicio(int linha, int coluna) {
		return setInicio(new Posicao(linha, coluna));
	}
	
	public MapaBuilder setInicio(Posicao inicio){
		this.inicio = inicio;
		return this;
	}

	public MapaBuilder setFinal(int linha, int coluna) {
		return setFinal(new Posicao(linha, coluna));
	}
	
	public MapaBuilder setFinal(Posicao fim) {
		this.fim = fim;
		return this;
	}

	public MapaBuilder addMuro(int linha, int coluna) {
		muro.add(new Posicao(linha, coluna, Posicao.Tipo.MURO));
		return this;
	}
	
	public MapaBuilder addMuro(Posicao posicaoMuro) {
		posicaoMuro.tipo = Posicao.Tipo.MURO;
		muro.add(posicaoMuro);
		return this;
	}

	public Mapa build() {
		if (inicio == null)
			throw new IllegalStateException("Falta informar o início.");
		if (fim == null)
			throw new IllegalStateException("Falta informar o fim.");
		if (colunas == 0)
			throw new IllegalStateException("0 colunas.");
		if (linhas == 0)
			throw new IllegalStateException("0 linhas.");
		return new MapaImpl(inicio, fim, linhas, colunas, muro);
	}

}
