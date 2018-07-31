package assess;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class PostmanTableModel extends AbstractTableModel implements TableModelListener {

	private static final long serialVersionUID = 5767402190172442585L;

	/** Table Date */
	private Object[][] tableData;
	
	/** Table Column Name */
	private static final String[] columnNames = {"Folder", "Name", "Method", "URL"};
	
	/** Table Row Count */
	public int tableRowCount;
	
	/** Table Column Count */
	public static final int TABLE_COLUMN_COUNT = 4;
	
	/** Table column index */
	public static final int FOLDER_NAME_INDEX = 0;
	public static final int NAME_COLUMN_INDEX = 1;
	public static final int METHOD_COLUMN_INDEX = 2;
	public static final int URL_COLUMN_INDEX = 3;
	
	public PostmanTableModel(int tableRowCount) {
		this.tableRowCount = tableRowCount;
		tableData = new Object[tableRowCount][TABLE_COLUMN_COUNT];
		for(int i = 0; i < tableRowCount; i++) {
			for(int j = 0; j < TABLE_COLUMN_COUNT; j++)
				tableData[i][j] = new String("");
		}

		// add listener
		addTableModelListener(this);
	}
	
	public Object[][] getTabledata() {
		return this.tableData;
	}
 			
	@Override
	public int getColumnCount() {
		return TABLE_COLUMN_COUNT;
	}

	@Override
	public int getRowCount() {
		return tableRowCount;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return tableData[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex == NAME_COLUMN_INDEX || columnIndex == FOLDER_NAME_INDEX)
			return true;
		else 
			return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		tableData[rowIndex][columnIndex]=aValue;
		fireTableDataChanged();
	}

	//
	// implement TableModelListener
	//
	@Override
	public void tableChanged(TableModelEvent e) {
	}
}