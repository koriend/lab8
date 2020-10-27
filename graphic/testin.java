package graphic;

import javax.swing.*;
import java.awt.*;

public class testin {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JPanel panel = new JPanel();
        //StarButton pl = new StarButton();
        MachineButton m = new MachineButton();
        //th.setBorderPainted(false);
       // th.setContentAreaFilled(false);
        panel.add(m);
        frame.add(panel);
        frame.setVisible(true);
    }


}
