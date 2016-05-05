package bfsdfs;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collection;

import model.Grafo;
import model.Vertice;

import org.junit.Before;
import org.junit.Test;

import caminho.Caminho;
import caminho.conexidade.VerificadorConexidadeFacade;
import testeutil.TesteUtil;

public class BfsDfsTest {

    private Grafo grafo, grafoConexo, grafo2, grafo3;
    
    @Before
    public void setUp() throws Exception {
	grafo = TesteUtil.importaGrafo( new File("grafo.xml") );
	grafoConexo = TesteUtil.importaGrafo( new File("grafoConexo.xml") );
	grafo2 = TesteUtil.importaGrafo( new File("grafo2.xml") );
	grafo3 = TesteUtil.importaGrafo( new File("grafo3.xml") );
    }

    @Test
    public void test_bfs() {
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafo, "Z", "G"), "");
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafo, "A", "G"), "ABDEFG");
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafo, "A", "C"), "ABDEFGC");
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafo, "A", "A"), "A");
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafoConexo, "A", "B"), "ACEDB");
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafo3, "F", "M"), "FGELKNIM");
	TesteUtil.assertCaminho(Caminho.BFS.encontraCaminho(grafo3, "B", "K"), "BCDAEFGLK");
    }
    
    @Test
    public void test_dfs() {
	TesteUtil.assertCaminho(Caminho.DFS.encontraCaminho(grafo, "A", "G"), "ABDEG");
	TesteUtil.assertCaminho(Caminho.DFS.encontraCaminho(grafoConexo, "A", "B"), "ACEDB");
	TesteUtil.assertCaminho(Caminho.DFS.encontraCaminho(grafo2, "A", "G"), "ABDEIJFG");
	TesteUtil.assertCaminho(Caminho.DFS.encontraCaminho(grafo3, "B", "H"), "");
	TesteUtil.assertCaminho(Caminho.DFS.encontraCaminho(grafo3, "B", "K"), "BCADEFGLK");
    }
    
    @Test
    public void test_sem_caminho() {
	Collection<Vertice> caminho = Caminho.DFS.encontraCaminho(grafo, "G", "A");
	TesteUtil.assertCaminho(caminho, "");
    }

    @Test
    public void test_conexidade() {
	assertFalse(VerificadorConexidadeFacade.isConexo(grafo));
	assertTrue(VerificadorConexidadeFacade.isConexo(grafoConexo));
    }

}
