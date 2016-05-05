package dijkstra;

import java.io.File;
import java.util.List;

import model.Grafo;
import model.Vertice;

import org.junit.Before;
import org.junit.Test;

import testeutil.TesteUtil;

public class DijkstraTest {

    private Grafo grafoVolta, grafoMinimo;

    @Before
    public void setUp() throws Exception {
	grafoVolta = TesteUtil.importaGrafo(new File("grafoDijkstraVolta.xml"));
	grafoMinimo = TesteUtil.importaGrafo(new File("grafoDijkstraMinimo.xml"));
    }

    @Test
    public void test() {
	Dijkstra dijkstra = Dijkstra.instance(grafoMinimo);
	List<Vertice> caminho = dijkstra.de("A").ate("B").retornaCaminho();
	TesteUtil.assertCaminho(caminho, "ACB");
    }
    
    @Test
    public void test_caminho_simples() {
	Dijkstra dijkstra = Dijkstra.instance(grafoVolta);
	List<Vertice> caminho = dijkstra.de("A").ate("D").retornaCaminho();
	TesteUtil.assertCaminho(caminho, "ABD");
    }
    
    @Test(expected=IllegalStateException.class)
    public void distancias_sem_informar_origem(){
	Dijkstra.instance(grafoMinimo).retornaDistanciasParaDemaisVertices();
    }
    
    @Test(expected=IllegalStateException.class)
    public void test_falta_informar_o_caminho() {
	Dijkstra dijkstra = Dijkstra.instance(grafoMinimo);
	dijkstra.retornaCaminho();
    }

}
