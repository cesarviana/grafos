package view.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import dijkstra.Dijkstra.Coluna;

public class DijkstraTableModel extends AbstractTableModel implements
	TableModel {

    private static final int ESTIMATIVA = 0;
    private static final int PRECEDENTE = 1;
    private List<Coluna> colunas;

    public DijkstraTableModel(List<Coluna> colunas) {
	this.colunas = colunas;
    }

    @Override
    public int getRowCount() {
	return 2;
    }

    @Override
    public int getColumnCount() {
	return colunas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
	Coluna coluna = colunas.get(columnIndex);
	switch (rowIndex) {
	case ESTIMATIVA:
	    return coluna.getEstimativa();
	case PRECEDENTE:
	    return coluna.getPrecedente().getVertice();
	default:
	    break;
	}
	return "";
    }

    @Override
    public String getColumnName(int column) {
	return colunas.get(column).getVertice().getRotulo();
    }

}
