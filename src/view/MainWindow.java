package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import leitorxml.LeitorXmlFacade;
import model.Grafo;
import model.Vertice;
import planaridade.VerificadorPlanaridade;
import planaridade.VerificadorPlanaridadeFactory;
import view.model.AStarTableModel;
import view.model.DijkstraTableModel;
import view.model.MainWindowModel;
import astar.AStar;
import astar.Mapa;
import caminho.Caminho;
import caminho.conexidade.VerificadorConexidadeFacade;
import coloracao.Coloracao;
import coloracao.ColoracaoFactory;
import coloracao.ColoracaoListener;
import dijkstra.Dijkstra;

public class MainWindow {

	private JFrame frame;
	private final Action importarAction = new ImportarGraphMaxAction();
	private final Action importarAEstrelaAction = new ImportarAStarAction();
	private final Action bfsAction = new BFSAction();
	private final Action dfsAction = new DFSAction();
	private final Action conectividadeAction = new ConectividadeAction();
	private final Action actionDistancias = new DistanciasAction();
	private final Action encontrarCaminho = new AStarCaminhoAction();
	private final Action planaridadeAction = new PlanaridadeAction();
	private final Action colorirAction = new ColorirAction();
	private JLabel lblResultadoExecucao;
	private final MainWindowModel viewModel = new MainWindowModel();
	private Coloracao coloracao = ColoracaoFactory.cria();
	private JTextField textFieldOrigem;
	private JTextField textFieldDestino;
	private JTextField textFieldOrigemDijkstra;
	private JTable table;
	private JLabel lblAcaoExecutada;
	private JLabel lblLog;
	private JTable tabelaAStar;
	private JPanelGrafo panelGrafoColoracao;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainWindow() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 539, 309);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelBfsDfs = new JPanel();
		tabbedPane.addTab("BFS/DFS/Conectividade/Planaridade", null,
				panelBfsDfs, null);
		panelBfsDfs.setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		panelBfsDfs.add(panelCentro);
		panelCentro.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panelCentro.add(panel_1, BorderLayout.SOUTH);

		JButton btnBfs = new JButton("BFS");
		panel_1.add(btnBfs);
		btnBfs.setAction(bfsAction);

		JButton btnDfs = new JButton("DFS");
		btnDfs.setAction(dfsAction);
		panel_1.add(btnDfs);

		JButton btnConectividade = new JButton("Conectividade");
		btnConectividade.setAction(conectividadeAction);
		panel_1.add(btnConectividade);

		JButton btnPlanaridade = new JButton(planaridadeAction);
		panel_1.add(btnPlanaridade);

		JPanel panel_2 = new JPanel();
		panelCentro.add(panel_2, BorderLayout.NORTH);

		JLabel lblOrigem = new JLabel("Origem");
		panel_2.add(lblOrigem);

		textFieldOrigem = TextFieldApenasMaiusculas.createJTextField();
		panel_2.add(textFieldOrigem);

		JLabel lblDestino = new JLabel("Destino");
		panel_2.add(lblDestino);

		textFieldDestino = TextFieldApenasMaiusculas.createJTextField();
		panel_2.add(textFieldDestino);
		textFieldDestino.setColumns(10);

		JPanel panel_8 = new JPanel();
		panelCentro.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));

		lblResultadoExecucao = new JLabel();
		panel_8.add(lblResultadoExecucao);
		lblResultadoExecucao.setFont(new Font("Consolas", Font.BOLD, 16));
		lblResultadoExecucao.setHorizontalAlignment(SwingConstants.CENTER);

		lblAcaoExecutada = new JLabel("");
		lblAcaoExecutada.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblAcaoExecutada, BorderLayout.NORTH);

		JPanel panelDijkstra = new JPanel();
		tabbedPane.addTab("Dijkstra", null, panelDijkstra, null);
		panelDijkstra.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panelDijkstra.add(panel_3, BorderLayout.NORTH);

		JLabel label = new JLabel("Origem");
		panel_3.add(label);

		textFieldOrigemDijkstra = TextFieldApenasMaiusculas.createJTextField();
		textFieldOrigemDijkstra.setColumns(10);
		panel_3.add(textFieldOrigemDijkstra);

		JPanel panel_4 = new JPanel();
		panelDijkstra.add(panel_4, BorderLayout.SOUTH);

		JButton btnDistncias = new JButton(actionDistancias);
		panel_4.add(btnDistncias);

		JPanel panel = new JPanel();
		panelDijkstra.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(30, 30));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JPanel panelAStar = new JPanel();
		tabbedPane.addTab("A*", null, panelAStar, null);
		panelAStar.setLayout(new BorderLayout(0, 0));

		JPanel panel_6 = new JPanel();
		panelAStar.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_6.add(scrollPane_1);

		tabelaAStar = new JTable();
		scrollPane_1.setViewportView(tabelaAStar);

		JPanel panel_7 = new JPanel();
		panelAStar.add(panel_7, BorderLayout.SOUTH);

		JButton button = new JButton(encontrarCaminho);
		panel_7.add(button);
		
		JPanel panelColoracao = new JPanel();
		tabbedPane.addTab("Coloração", null, panelColoracao, null);
		panelColoracao.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		panelColoracao.add(panel_9, BorderLayout.SOUTH);
		
		JButton btnColorir = new JButton(colorirAction);
		panel_9.add(btnColorir);
		
		panelGrafoColoracao = new JPanelGrafo();
		panelGrafoColoracao.setSize(new Dimension(800, 600));
		panelGrafoColoracao.setBackground(Color.blue);
		panelColoracao.add(panelGrafoColoracao, BorderLayout.NORTH);
		JScrollPane scrollPane_2 = new JScrollPane(panelGrafoColoracao);
		panelColoracao.add(scrollPane_2, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5, BorderLayout.SOUTH);

		lblLog = new JLabel("Importe o grafo e execute os algoritmo.");
		panel_5.add(lblLog);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnImportar = new JMenu("Importar");
		menuBar.add(mnImportar);

		JMenuItem mntmNewMenuItem = new JMenuItem(importarAction);
		mnImportar.add(mntmNewMenuItem);

		JMenuItem mntmAestrela = new JMenuItem(importarAEstrelaAction);
		mnImportar.add(mntmAestrela);
	}

	private void erro(String message) {
		JOptionPane.showMessageDialog(frame, message, "",
				JOptionPane.ERROR_MESSAGE);
	}

	private void log(String message) {
		lblLog.setText(message);
	}

	private abstract class PrecisadorGrafo extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				validaTemGrafo();
				executa(e);
				informaAcaoExecutada();
			} catch (Exception e2) {
				e2.printStackTrace();
				erro(e2.getMessage());
			}
		}

		private void informaAcaoExecutada() {
			lblAcaoExecutada.setText(getValue(NAME).toString());
		}

		public abstract void executa(ActionEvent e);

		private void validaTemGrafo() {
			if (viewModel.getGrafo() == Grafo.NULO)
				throw new NullPointerException(
						"Não há grafo. Importe um grafo!");
		}
	}

	private abstract class AlgoritmoBuscaCaminhoAction extends PrecisadorGrafo {
		public AlgoritmoBuscaCaminhoAction() {
			putValue(NAME, "Algoritmo busca caminho");
			putValue(SHORT_DESCRIPTION, "Executar algoritmo");
		}

		public void executa(ActionEvent e) {
			validaInputs();
			List<Vertice> caminho = encontraCaminho();
			lblResultadoExecucao.setText(caminho.toString());
		}

		private void validaInputs() {
			String mensagemErro = "";
			if (textFieldOrigem.getText().isEmpty()) {
				mensagemErro += "\nInforme a origem!";
			} else if (textFieldDestino.getText().isEmpty()) {
				mensagemErro += "\nInforme o destino!";
			}
			if (!mensagemErro.isEmpty())
				throw new RuntimeException(mensagemErro);
		}

		protected abstract List<Vertice> encontraCaminho();

	}

	private class ImportarGraphMaxAction extends AbstractAction {
		public ImportarGraphMaxAction() {
			putValue(NAME, "Graph Max");
			putValue(SHORT_DESCRIPTION, "grafo");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				importarEhMostrarLog();
			} catch (Exception e2) {
				erro(e2.getMessage());
			}
		}

		private void importarEhMostrarLog() {
			viewModel.setGrafo(LeitorXmlFacade.lerGrafoGraphMax());
			panelGrafoColoracao.desenha(viewModel.getGrafo());
			log("Grafo importado.\n "
					+ viewModel.getGrafo().getVertices().toString());
		}
	}

	private class BFSAction extends AlgoritmoBuscaCaminhoAction {

		public BFSAction() {
			putValue(NAME, "BFS");
			putValue(SHORT_DESCRIPTION, "Executar BFS");
		}

		@Override
		protected List<Vertice> encontraCaminho() {
			return Caminho.BFS.encontraCaminho(viewModel.getGrafo(),
					textFieldOrigem.getText(), textFieldDestino.getText());
		}

	}

	private class DFSAction extends AlgoritmoBuscaCaminhoAction {

		public DFSAction() {
			putValue(NAME, "DFS");
			putValue(SHORT_DESCRIPTION, "Executar DFS");
		}

		@Override
		protected List<Vertice> encontraCaminho() {
			return Caminho.DFS.encontraCaminho(viewModel.getGrafo(),
					textFieldOrigem.getText(), textFieldDestino.getText());
		}

	}

	private class ConectividadeAction extends PrecisadorGrafo {

		public ConectividadeAction() {
			putValue(NAME, "Conectividade");
			putValue(SHORT_DESCRIPTION, "Verificar se o grafo é conexo");
		}

		@Override
		public void executa(ActionEvent e) {
			verificaSeConexoEhMostraResultado();
		}

		private void verificaSeConexoEhMostraResultado() {
			boolean conexo = VerificadorConexidadeFacade.isConexo(viewModel
					.getGrafo());
			String resultado = conexo ? "O grafo é conexo."
					: "O grafo NÃO é conexo.";
			lblResultadoExecucao.setText(resultado);
		}

	}

	private class DistanciasAction extends PrecisadorGrafo {
		public DistanciasAction() {
			putValue(NAME, "Distâncias");
			putValue(SHORT_DESCRIPTION,
					"Encontra a distância para os demais vértices");
		}

		public void executa(ActionEvent e) {
			calculaEhMostraDistancias();
		}

		private void calculaEhMostraDistancias() {
			validaSeExisteGrafoEhOrigem();

			String origem = textFieldOrigemDijkstra.getText();
			List<Dijkstra.Coluna> colunas = new ArrayList<>(Dijkstra
					.instance(viewModel.getGrafo()).de(origem)
					.retornaDistanciasParaDemaisVertices());
			table.setModel(new DijkstraTableModel(colunas));
		}

		private void validaSeExisteGrafoEhOrigem() {
			if (textFieldOrigemDijkstra.getText().isEmpty())
				throw new NullPointerException("Informe a origem!");
		}
	}

	private class ImportarAStarAction extends AbstractAction {
		public ImportarAStarAction() {
			putValue(NAME, "A*");
			putValue(SHORT_DESCRIPTION, "Importar grafo A*");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				viewModel.setMapa(LeitorXmlFacade.lerAStar());
				log("Mapa importado:" + viewModel.getMapa());
			} catch (Exception e2) {
				erro(e2.getMessage());
			}
		}
	}

	private class AStarCaminhoAction extends AbstractAction {
		public AStarCaminhoAction() {
			putValue(NAME, "Encontrar caminho");
			putValue(SHORT_DESCRIPTION,
					"Encontrar caminho do ponto final ao inicial");
		}

		public void actionPerformed(ActionEvent e) {
			if (viewModel.getMapa() == Mapa.NULO)
				log("Não há mapa importado. Importe um mapa A*.");
			else {
				AStar aStar = new AStar();
				tabelaAStar.setModel(new AStarTableModel(aStar
						.encontraCaminho(viewModel.getMapa())));
				log(aStar.encontrouCaminho() ? "Caminho encontrado"
						: "Caminho não encontrado");
			}
		}
	}

	private class PlanaridadeAction extends PrecisadorGrafo {
		public PlanaridadeAction() {
			putValue(NAME, "Planaridade");
			putValue(SHORT_DESCRIPTION, "Verificar se o grafo é planar");
		}

		@Override
		public void executa(ActionEvent e) {
			VerificadorPlanaridade verificadorPlanaridade = VerificadorPlanaridadeFactory
					.create();
			boolean planar = verificadorPlanaridade.isPlanar(viewModel
					.getGrafo());
			String msg = planar ? "O grafo é planar" : "O grafo NÃO é planar";
			lblResultadoExecucao.setText(msg);
		}
	}
	
	private class ColorirAction extends PrecisadorGrafo {
		public ColorirAction() {
			putValue(NAME, "Colorir");
			putValue(SHORT_DESCRIPTION, "Colorir matriz");
		}
		@Override
		public void executa(ActionEvent e) {
			coloracao.addListener(new ColoracaoListener() {
				@Override
				public void coloriu(Grafo grafo, Vertice vertice) {
					panelGrafoColoracao.desenha( grafo );
				}
			});
			coloracao.executaPasso(viewModel.getGrafo());
		}
	}
	
}
