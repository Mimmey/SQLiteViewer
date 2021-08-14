package viewer;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    Object[][] data;
    final String[] columns = {"connect_id", "first_name", "last_name", "email", "phone"};

    public MyTableModel(Object[][] data) {
        super();
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}