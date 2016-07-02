package coloracao;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import model.impl.VerticePadrao;

public class VerticeColorido extends VerticePadrao {

	public VerticeColorido(String rotulo, int relId) {
		super(rotulo, relId);
	}

	public VerticeColorido(int poxX, int poxY, String rotulo, int relId) {
		super(poxX, poxY, rotulo, relId);
	}

	public static final Color COR_NULA = new Color(0, 0, 0, 0);
	private Color cor = COR_NULA;

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public boolean semCor() {
		return cor == COR_NULA;
	}

	public int grauSaturacao() {
		Set<Color> cores = new HashSet<>();
		getAdjacentes().stream().filter(v -> v instanceof VerticeColorido)
				.map(v -> (VerticeColorido) v).filter(v -> !v.semCor())
				.forEach(v -> cores.add(v.getCor()));
		return cores.size();
	}

	public boolean temCorAdjacente(Color color) {
		return getAdjacentes().stream()
				.filter(v -> v instanceof VerticeColorido)
				.map(v -> (VerticeColorido) v)
				.anyMatch(v -> v.getCor().equals(color));
	}

}
