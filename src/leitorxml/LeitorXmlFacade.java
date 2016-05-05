package leitorxml;

import java.io.File;
import java.util.concurrent.CancellationException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import leitorxml.impl.LeitorGraphMax;
import leitorxml.impl.LeitorXmlAStar;
import model.Grafo;
import astar.Mapa;

public class LeitorXmlFacade {

	private static final LeitorXml<Grafo> leitor = new LeitorGraphMax();
	private static final LeitorXml<Mapa> leitorAStar = new LeitorXmlAStar();
	private static final SeletorArquivo seletorArquivo = new SeletorArquivo();
	
	public static Grafo lerGrafoGraphMax() {
		return leitor.ler(seletorArquivo.escolheArquivo());
	}
	
	public static Mapa lerAStar() {
		return leitorAStar.ler(seletorArquivo.escolheArquivo());
	}

	private static class SeletorArquivo {

		private static final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(
				"Arquivo XML", "xml");;
		private static final JFileChooser fileChooser = new JFileChooser();
		private File selectedFile;

		private File escolheArquivo() {
			fileChooser.setFileFilter(extensionFilter);
			fileChooser.setCurrentDirectory(selectedFile);
			int returnVal = fileChooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				return selectedFile;
			}
			throw new CancellationException("A escolha de xml foi cancelada");
		}
	}

}
