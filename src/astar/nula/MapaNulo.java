package astar.nula;

import java.util.Collection;
import java.util.Collections;

import astar.Mapa;
import astar.Posicao;

public class MapaNulo implements Mapa {

	@Override
	public Posicao getInicio() {
		return Posicao.NULL;
	}

	@Override
	public Posicao getFim() {
		return Posicao.NULL;
	}

	@Override
	public Collection<Posicao> getVizinhosAcessiveisDe(Posicao posicao) {
		return Collections.emptyList();
	}

	@Override
	public int getLinhas() {
		return 0;
	}

	@Override
	public int getColunas() {
		return 0;
	}

}
