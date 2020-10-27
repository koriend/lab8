package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class StarButton  extends JButton {
    @Override
    public void paintComponent(Graphics g1) {

        //tringle
        Graphics2D g2 = (Graphics2D) g1;
        int x1Points[] = {40, 55,  30, 61,  75, 91, 120, 95, 110, 75};
        int y1Points[] = {115, 73, 55, 55, 15, 55, 55, 73, 115, 88};
        GeneralPath polygon =
                new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                        x1Points.length);
        polygon.moveTo(x1Points[0], y1Points[0]);

        for (int index = 1; index < x1Points.length; index++) {
            polygon.lineTo(x1Points[index], y1Points[index]);
        };



        polygon.closePath();
        g2.setColor(Color.BLACK);
        g2.fill(polygon);
        g2.draw(polygon);

    }



    public Dimension getPreferredSize ()
    {
        return new Dimension ( 150   , 150 );
    }
}
