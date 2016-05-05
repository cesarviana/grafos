package leitorxml.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import leitorxml.LeitorXml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import astar.Mapa;
import astar.MapaBuilder;
import astar.Posicao;

public class LeitorXmlAStar implements LeitorXml<Mapa> {

	private Document doc;

	@Override
	public Mapa ler(File xml) {
		try {
			return lerXml(xml);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao ler xml A*.", e);
		}
	}

	private Mapa lerXml(File xml) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(xml);
		doc.getDocumentElement().normalize();

		MapaBuilder builder = new MapaBuilder();

		builder.setLinhas(Integer.parseInt(nodeValue("LINHAS")));
		builder.setColunas(Integer.parseInt(nodeValue("COLUNAS")));
		builder.setInicio(Posicao.parse(nodeValue("INICIAL")));
		builder.setFinal(Posicao.parse(nodeValue("FINAL")));

		NodeList muro = doc.getElementsByTagName("MURO");
		for (int i = 0; i < muro.getLength(); i++) {
			Node itemMuro = muro.item(i);
			builder.addMuro(Posicao.parse(itemMuro.getFirstChild()
					.getNodeValue()));
		}

		return builder.build();
	}

	private String nodeValue(String tagname) {
		NodeList nodeList = doc.getElementsByTagName(tagname);
		if (nodeList.getLength() == 0)
			throw new IllegalStateException("Não foi encontrada a tag "
					+ tagname);
		Node item = nodeList.item(0);
		String nodeValue = item.getFirstChild().getNodeValue();
		return nodeValue;
	}

}
