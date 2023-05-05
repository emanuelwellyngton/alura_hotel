package modelos;

import java.awt.Component;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class ColunaNaoEditavel extends DefaultTableModel {
	
	private JTable tabela;

	public ColunaNaoEditavel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ColunaNaoEditavel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
		// TODO Auto-generated constructor stub
	}

	public ColunaNaoEditavel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public ColunaNaoEditavel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public ColunaNaoEditavel(Vector<?> columnNames, int rowCount) {
		super(columnNames, rowCount);
		// TODO Auto-generated constructor stub
	}

	public ColunaNaoEditavel(Vector<? extends Vector> data, Vector<?> columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}

	public ColunaNaoEditavel(JTable tabela) {
		this.tabela = tabela;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		
		if(tabela.getName() == "Reservas") {
			if(column == 0 || column == 3 || column == 5)
				return false;
		}
		
		if(tabela.getName() == "Hóspedes") {
			if(column == 0 || column == 5)
				return false;
		}
		
		return super.isCellEditable(row, column);
	}

}
