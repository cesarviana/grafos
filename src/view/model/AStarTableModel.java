package view.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import astar.Passo;

public class AStarTableModel extends AbstractTableModel implements TableModel {

	private List<Passo> passos;
	private String[] linhas = new String[] { "F", "G", "H", "Linha,Coluna" };

	public AStarTableModel(List<Passo> passos) {
		this.passos = passos;
	}

	@Override
	public int getRowCount() {
		return linhas.length;
	}

	@Override
	public int getColumnCount() {
		return passos.size() + 1;
	}
	
	@Override
	public String getColumnName(int column) {
		if(column==0) return "";
		return String.valueOf(--column);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return linhas[rowIndex];
		}
		Passo passo = passos.get(--columnIndex);
		switch (rowIndex) {
		case 0:
			return passo.f();
		case 1:
			return passo.g;
		case 2:
			return passo.h;
		case 3:
			return passo.posicao;
		}
		return "";
	}

}
