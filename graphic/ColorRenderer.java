package graphic;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(hasFocus) {
            setBackground(new Color(115, 89, 73));
            setForeground(Color.WHITE);
        } else if (isSelected) {
            setBackground(new Color(242, 141, 82));
            setForeground(Color.BLACK);
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
           // setBackground(new Color(242, 242, 242));
            //setForeground(Color.BLACK);
        }
        return this;
    }
}
