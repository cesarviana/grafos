package astar;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class AStarTest {

	@Test
	public void test() {
		MapaBuilder builder = new MapaBuilder();
		Mapa mapa = builder.setLinhas(10).setColunas(15).setInicio(1, 2)
				.setFinal(2, 11)

				.addMuro(1, 4).addMuro(1, 5).addMuro(1, 6)

				.addMuro(1, 7).addMuro(2, 7).addMuro(3, 7).addMuro(4, 7)
				.addMuro(5, 7).addMuro(6, 7)

				.addMuro(6, 4).addMuro(6, 5).addMuro(6, 6).addMuro(6, 7)

				.addMuro(3, 5)

				.addMuro(4, 5).addMuro(4, 6).addMuro(4, 7)

				.build();

		List<Passo> passos = new AStar().encontraCaminho(mapa);
		assertCaminho(passos);
		passos.forEach(System.out::println);

	}

	private void assertCaminho(List<Passo> passos) {
		String ss[] = new String[]{
				"g=0;h=100;1,2",
				"g=14;h=80;2,3",
				"g=24;h=70;2,4",
				"g=34;h=60;2,5",
				"g=44;h=50;2,6",
				"g=54;h=60;3,6",
				"g=64;h=50;2,6"
			};
		for (int i = 0; i < ss.length; i++) {
			assertEquals(ss[i], passos.get(i).toString());
		}
	}

}
