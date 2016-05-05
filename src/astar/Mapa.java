package astar;

import java.util.Collection;

import astar.nula.MapaNulo;

public interface Mapa {

	public Mapa NULO = new MapaNulo();

	public Posicao getInicio();

	public Posicao getFim();

	public Collection<Posicao> getVizinhosAcessiveisDe(Posicao posicao);

	public int getLinhas();
	
	public int getColunas();

}
