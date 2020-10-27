package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class PlanetButton extends JButton {


    @Override
    protected void paintComponent(Graphics g1) {

        int x=75;
        int y=75;
        int R=60;
        double kappa = 0.5522847498;

        Graphics2D g2 = (Graphics2D) g1;

        GeneralPath circle = new GeneralPath();
        circle.moveTo(x, y-R); // move to A
        circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
        circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
        circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
        circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
        circle.closePath();

        g2.setColor(Color.BLACK);
        g2.fill(circle);
        g2.draw(circle);
    }

    public Dimension getPreferredSize ()
    {
        return new Dimension ( 150   , 150 );
    }
}
