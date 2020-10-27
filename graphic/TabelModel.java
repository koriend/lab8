package graphic;

import javax.swing.table.DefaultTableModel;

public class TabelModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Class getColumnClass(int i) {
        if(i == 2) return Integer.class;
        else return String.class;
    }
}
