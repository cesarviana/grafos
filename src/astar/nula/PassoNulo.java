package astar.nula;

import astar.Passo;
import astar.Posicao;

public class PassoNulo extends Passo {

	public PassoNulo(Posicao posicao, int g, int h) {
		this();
	}

	public PassoNulo() {
		super(Posicao.NULL, 0, 0);
	}

}
