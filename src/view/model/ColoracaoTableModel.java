package view.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.table.AbstractTableModel;

import model.Grafo;
import coloracao.VerticeColorido;

public class ColoracaoTableModel extends AbstractTableModel {

	private List<VerticeColorido> vertices;

	public ColoracaoTableModel(Grafo grafo) {
		vertices = new ArrayList<VerticeColorido>();
		vertices.addAll(grafo.getVertices().stream()
				.filter(v -> v instanceof VerticeColorido)
				.map(v -> (VerticeColorido) v).collect(Collectors.toList()));
	}

	@Override
	public int getRowCount() {
		return vertices.size();
	}

	@Override
	public int getColumnCount() {
		return vertices.size() + 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0)
			return getColumnName(rowIndex + 1);

		if (columnIndex == vertices.size() + 1)
			return vertices.get(rowIndex).grau();

		if (rowIndex == columnIndex - 1)
			return "-";

		if (vertices.get(rowIndex).getAdjacentes()
				.contains(vertices.get(columnIndex - 1))) {
			return 1;
		}

		return "";

	}

	@Override
	public String getColumnName(int column) {
		if (column == 0)
			return "";
		if (column == vertices.size() + 1)
			return "grau";
		return vertices.get(column - 1).getRotulo();
	}

}
