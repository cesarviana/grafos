package astar;

import astar.nula.PosicaoNula;

public class Posicao {

	private static final int CUSTO_RETO = 10;
	private static final int CUSTO_DIAGONAL = 14;
	public static final Posicao NULL = new PosicaoNula();
	public int linha, coluna;
	public Tipo tipo;

	public Posicao(int linha, int coluna) {
		this(linha, coluna, Tipo.LIVRE);
	}

	public Posicao(int linha, int coluna, Tipo tipo) {
		this.linha = linha;
		this.coluna = coluna;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return linha + "," + coluna;
	}

	public enum Tipo {
		INEXISTENTE {
			@Override
			public boolean isNavegavel() {
				return false;
			}
		},
		MURO {
			@Override
			boolean isNavegavel() {
				return false;
			}
		},
		LIVRE {
			@Override
			boolean isNavegavel() {
				return true;
			}
		};
		abstract boolean isNavegavel();
	}

	public boolean isDiagonalDe(Posicao posicao) {
		return linha != posicao.linha && coluna != posicao.coluna;
	}

	public boolean isAcessivel() {
		return tipo.isNavegavel();
	}

	public Posicao cimaEsquerda() {
		return new Posicao(linha - 1, coluna - 1);
	}

	public Posicao cimaDireita() {
		return new Posicao(linha - 1, coluna + 1);
	}

	public Posicao baixoEsquerda() {
		return new Posicao(linha + 1, coluna - 1);
	}

	public Posicao baixoDireita() {
		return new Posicao(linha + 1, coluna + 1);
	}

	public Posicao cima() {
		return new Posicao(linha - 1, coluna);
	}

	public Posicao baixo() {
		return new Posicao(linha + 1, coluna);
	}

	public Posicao esquerda() {
		return new Posicao(linha, coluna - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicao other = (Posicao) obj;
		return other.linha == linha && other.coluna == coluna;
	}

	public Posicao direita() {
		return new Posicao(linha, coluna + 1);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + coluna;
		result = prime * result + linha;
		return result;
	}

	public int custoPara(Posicao posicao) {
		return isDiagonalDe(posicao) ? CUSTO_DIAGONAL : CUSTO_RETO;
	}

	public static Posicao parse(String s) {
		try {
			String values[] = s.split(",");
			int linha = Integer.parseInt(values[0]);
			int coluna = Integer.parseInt(values[1]);
			return new Posicao(linha, coluna);
		} catch (Exception e) {
			throw new RuntimeException(
					"Falha ler string posicao " + s);
		}

	}
}
