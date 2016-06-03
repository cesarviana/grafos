package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import coloracao.VerticeColorido;
import model.Grafo;
import model.Vertice;

public class JPanelGrafo extends JComponent {

	private Grafo grafo = Grafo.NULO;
	private static final int L = 30;
	private static final int N = L / 2;
	public void desenha(Grafo grafo) {
		this.grafo = grafo;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		desenhaLinhas(g);
		desenhaBolas(g);
	}

	private void desenhaLinhas(Graphics g) {
		for(Vertice v : grafo.getVertices()){
			for(Vertice v2 : v.getAdjacentes()){
				g.drawLine(v.getPosX(), v.getPosY(), v2.getPosX(), v2.getPosY());
			}
		}
	}

	private void desenhaBolas(Graphics g) {
		grafo.getVertices().forEach(v->{
			VerticeColorido verticeColorido = (VerticeColorido)v;
			g.setColor(verticeColorido.getCor());
			int x = v.getPosX() - N;
			int y = v.getPosY() - N;
			g.fillOval(x, y, L, L);
			g.setColor(Color.black);
			g.drawOval(x, y, L, L);
			g.drawString(String.valueOf(verticeColorido.grauSaturacao()), x, y);
			g.drawString(String.valueOf(verticeColorido.getRotulo()), x + N, y + N);
		});
	}

}
