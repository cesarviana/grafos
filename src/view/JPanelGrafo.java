package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;

import model.Grafo;
import model.Vertice;
import coloracao.VerticeColorido;

public class JPanelGrafo extends JComponent {

	private Grafo grafo = Grafo.NULO;
	private List<Vertice> caminho;
	private static final int L = 30;
	private static final int N = L / 2;

	public void desenha(Grafo grafo) {
		this.grafo = grafo;
		repaint();
	}

	public void desenha(Grafo grafo, List<Vertice> caminho) {
		this.grafo = grafo;
		this.caminho = caminho;
	}

	@Override
	protected void paintComponent(Graphics g) {
		desenhaLinhas(g);
		desenhaBolas(g);
		desenhaCaminho(g, Color.RED);
	}

	private void desenhaCaminho(Graphics g, Color red) {
		for(int i=0; i<caminho.size()-1; i++){
			g.setColor(red);
			Vertice v = caminho.get(i);
			Vertice v2 = caminho.get(i+1);
			desenhaLinha(g, v, v2);
			double peso = v.getArestaCom(v2.getRotulo()).getPeso();
			int x = (int) (v.getPosX() + v2.getPosX()) / 2;
			int y = (int) (v.getPosY() + v2.getPosY()) / 2;
			g.drawString(String.valueOf(peso), x, y);
		}
	}

	private void desenhaLinhas(Graphics g) {
		Collection<Vertice> vertices = grafo.getVertices();
		desenhaLinhas(g, vertices, Color.DARK_GRAY);
	}

	private void desenhaLinhas(Graphics g, Collection<Vertice> vertices, Color c) {
		if (vertices == null || vertices.isEmpty())
			return;
		g.setColor(c);
		for (Vertice v : vertices) {
			for (Vertice v2 : v.getAdjacentes()) {
				desenhaLinha(g, v, v2);
			}
		}
	}

	private void desenhaLinha(Graphics g, Vertice v, Vertice v2) {
		g.drawLine(v.getPosX(), v.getPosY(), v2.getPosX(), v2.getPosY());
	}

	private void desenhaBolas(Graphics g) {
		grafo.getVertices().forEach(
				v -> {
					VerticeColorido verticeColorido = (VerticeColorido) v;
					g.setColor(verticeColorido.getCor());
					int x = v.getPosX() - N;
					int y = v.getPosY() - N;
					g.fillOval(x, y, L, L);
					g.setColor(Color.black);
					g.drawOval(x, y, L, L);
					g.drawString(
							String.valueOf(verticeColorido.grauSaturacao()), x,
							y);
					g.drawString(String.valueOf(verticeColorido.getRotulo()), x
							+ N, y + N);
				});
	}

}
