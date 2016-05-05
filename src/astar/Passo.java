package astar;

import astar.nula.PassoNulo;

public class Passo {
	
	public static final Passo NULL = new PassoNulo();
	public Posicao posicao;
	public int g, h;

	public Passo(Posicao posicao, int g, int h) {
		super();
		this.posicao = posicao;
		this.g = g;
		this.h = h;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("g=").append(g).append(";");
		sb.append("h=").append(h).append(";");
		sb.append(posicao);
		return sb.toString();
	}

	public int f() {
		return g+h;
	}
	
}
