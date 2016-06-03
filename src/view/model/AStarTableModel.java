package view.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import astar.Passo;

public class AStarTableModel extends AbstractTableModel implements TableModel {

	private List<Passo> passos;
	private String[] colunas = new String[] { "i", "F", "G", "H", "Linha,Coluna" };

	public AStarTableModel(List<Passo> passos) {
		this.passos = passos;
	}

	@Override
	public int getRowCount() {
		return passos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Passo passo = passos.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return rowIndex;
		case 1:
			return passo.f();
		case 2:
			return passo.g;
		case 3:
			return passo.h;
		case 4:
			return passo.posicao;
		}
		return "";
	}

}
