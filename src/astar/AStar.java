package astar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class AStar {

	private List<Passo> passos = Collections.emptyList();
	private Stack<Passo> pilha;
	private Passo atual;
	private Mapa mapa = Mapa.NULO;
	private final Caminhador calculador = new Caminhador();

	public List<Passo> encontraCaminho(Mapa mapa) {
		this.mapa = mapa;
		passos = new ArrayList<>();
		pilha = new Stack<>();
		inicializa();
		while (naoChegouAoDestino() && temCaminhoASeguir()) {
			calculador.caminha();
		}
		return passos;
	}

	private void inicializa() {
		int g = 0;
		Passo primeiroPasso = new Passo(mapa.getInicio(), g,
				calculaH(mapa.getInicio()));
		atual = primeiroPasso;
		pilha.push(atual);
		passos.add(atual);
	}

	private int calculaH(Posicao posicao) {
		int h = 10 * (Math.abs(mapa.getFim().coluna - posicao.coluna) + Math
				.abs(mapa.getFim().linha - posicao.linha));
		return h;
	}

	private int calculaG(Posicao posicao) {
		Passo ultimoPasso = passos.get(passos.size() - 1);
		int custo = ultimoPasso.posicao.custoPara(posicao);
		return ultimoPasso.g + custo;
	}

	private boolean naoChegouAoDestino() {
		return atual.posicao.linha != mapa.getFim().linha
				|| atual.posicao.coluna != mapa.getFim().coluna;
	}

	private boolean temCaminhoASeguir() {
		return passos.stream().anyMatch(
				passo -> calculador
						.posicaoTemVizinhoAcessivelNaoAcessado(passo.posicao));
	}

	private class Caminhador {

		private Collection<Posicao> vizinhosAcessiveis;
		private Collection<Passo> passosPossiveis;

		private void caminha() {
			if (posicaoTemVizinhoAcessivelNaoAcessado(atual.posicao)) {
				caminhaParaFrente();
			} else {
				volta();
			}
		}

		private boolean posicaoTemVizinhoAcessivelNaoAcessado(Posicao posicao) {
			buscaVizinhosAcessiveis(posicao);
			return vizinhosAcessiveis.stream().anyMatch(
					vizinho -> naoAcessou(vizinho));
		}

		private void buscaVizinhosAcessiveis(Posicao posicao) {
			vizinhosAcessiveis = mapa.getVizinhosAcessiveisDe(posicao);
		}

		private boolean naoAcessou(Posicao posicao) {
			return passos.stream().noneMatch(
					passo -> passo.posicao.equals(posicao));
		}

		private void caminhaParaFrente() {
			calculaPassosPossiveisNaoAcessados();
			atual = passoDeMenorCusto();
			passos.add(atual);
			pilha.push(atual);
		}

		private void calculaPassosPossiveisNaoAcessados() {
			passosPossiveis = new ArrayList<>();
			vizinhosAcessiveis.forEach(vizinho -> {
				if (naoAcessou(vizinho))
					passosPossiveis.add(passoPara(vizinho));
			});
		}

		private Passo passoDeMenorCusto() {
			Comparator<Passo> comparator = (passo1, passo2) -> {
				if (passo1.f() == passo2.f())
					return 0;
				else if (passo1.f() > passo2.f())
					return 1;
				return -1;
			};
			return passosPossiveis.stream().min(comparator).get();
		}

		private Passo passoPara(Posicao vizinho) {
			int h = calculaH(vizinho);
			int g = calculaG(vizinho);
			return new Passo(vizinho, g, h);
		}

		private void volta() {
			pilha.pop();
			Passo anterior = pilha.peek();
			atual = passoPara(anterior.posicao);
			passos.add(atual);
		}

	}

	public boolean encontrouCaminho() {
		if (passos.isEmpty())
			return false;
		return passos.get(passos.size() - 1).posicao.equals(mapa.getFim());
	}

}
