package leitorxml.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import leitorxml.LeitorXml;
import model.Grafo;
import model.factory.ModelSimpleFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LeitorGraphMax implements LeitorXml<Grafo> {

	private File xml;

	@Override
	public Grafo ler(File xml) {
		this.xml = xml;
		Grafo grafo = ModelSimpleFactory.factory().criaGrafo();
		try {
			grafo = criaGrafo();
		} catch (Exception ex) {
			throw new RuntimeException("Falha ao importar grafo.", ex);
		}
		return grafo;
	}

	protected Grafo criaGrafo() throws ParserConfigurationException,
			SAXException, IOException {
		Grafo grafo = ModelSimpleFactory.factory().criaGrafo();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();
		Element nodeGrafo = (Element) doc.getElementsByTagName("Grafo").item(0);
		grafo.setDirigido(Boolean.parseBoolean(nodeGrafo
				.getAttribute("dirigido")));
		NodeList nodes = doc.getElementsByTagName("Vertice");
		if (nodes.getLength() == 0) {
			throw new IllegalArgumentException(
					"Você não está importando o xml correto. \n"
							+ "O xml esperado é aquele gerado a partir do botão 'Scripts' (na janela principal do programa). \n"
							+ "- Passos: \n"
							+ "1) Clique sobre o botão 'Scripts' na tela principal do GraphMax\n"
							+ "2) Acesse a aba 'XML' na janela que foi aberta\n"
							+ "3) Clique no botão 'Exportar'");
		} else {
			adicionaVertices(grafo, nodes);
			adicionaArestas(grafo, doc);
		}
		return grafo;
	}

	private void adicionaVertices(Grafo grafo, NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				int relId = Integer.parseInt(element.getAttribute("relId"));
				String rotulo = element.getAttribute("rotulo");
				int posX = Integer.parseInt(element.getAttribute("posX"));
				int posY = Integer.parseInt(element.getAttribute("posY"));
				grafo.adicionarVertice(posX, posY, rotulo, relId);
			}
		}
	}

	private void adicionaArestas(Grafo grafo, Document doc) {
		NodeList nodes;
		nodes = doc.getElementsByTagName("Aresta");
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				int idVertice1 = Integer.parseInt(element
						.getAttribute("idVertice1"));
				int idVertice2 = Integer.parseInt(element
						.getAttribute("idVertice2"));
				double peso = Double.parseDouble(element.getAttribute("peso"));
				grafo.adicionarAresta(idVertice1, idVertice2, peso);
			}
		}
	}

}
