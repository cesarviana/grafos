package astar.nula;

import astar.Posicao;

public class PosicaoNula extends Posicao {

	public PosicaoNula(int linha, int coluna) {
		super(linha, coluna);
	}

	public PosicaoNula() {
		this(0,0);
		super.tipo = Posicao.Tipo.INEXISTENTE;
	}

}
