package caixeiroviajante;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import leitorxml.impl.LeitorGraphMax;
import model.Grafo;
import model.Vertice;

import org.junit.Test;

public class CaixeiroViajanteTest {

	@Test
	public void test() {
		CaixeiroViajante caixeiro = new CaixeiroViajante();
		Grafo grafo = new LeitorGraphMax().ler(new File(
				"grafoCaixeiroViajante.xml"));
		List<Vertice> caminho = caixeiro.calcula(grafo);
		StringBuilder s = new StringBuilder();
		caminho.forEach(s::append);
		assertEquals("DCBAFED",caminho.toString());
	}

	@Test
	public void test2() {
		CaixeiroViajante caixeiro = new CaixeiroViajante();
		Grafo grafo = new LeitorGraphMax().ler(new File(
				"Caixeiro Viajante_XML para Teste.xml"));
		List<Vertice> caminho = caixeiro.calcula(grafo);
		StringBuilder s = new StringBuilder();
		caminho.forEach(s::append);
		assertEquals("[C, B, E, A, F, D, C]",caminho.toString());//[C, E, A, F, B, D, C]
		assertEquals(121, caixeiro.calculaDistancia(caminho),0);
	}

	
}
