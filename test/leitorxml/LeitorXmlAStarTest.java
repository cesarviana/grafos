package leitorxml;

import static org.junit.Assert.*;

import java.io.File;

import leitorxml.impl.LeitorXmlAStar;

import org.junit.Test;

import astar.Mapa;

public class LeitorXmlAStarTest {

	@Test
	public void importa(){
		Mapa mapa = new LeitorXmlAStar().ler(new File("A_Star_MODELO.xml"));
		String fim = "8,8";
		String inicio = "3,4";
		int colunas = 15;
		int linhas = 10;
		assertImportacaoCorreta(mapa, fim, inicio, colunas, linhas);
		
	}

	private void assertImportacaoCorreta(Mapa mapa, String fim, String inicio,
			int colunas, int linhas) {
		assertEquals(fim,mapa.getFim().toString());
		assertEquals(inicio,mapa.getInicio().toString());
		assertEquals(colunas,mapa.getColunas());
		assertEquals(linhas,mapa.getLinhas());
	}
	
}
