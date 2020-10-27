package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class MachineButton extends JButton {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int x1Points[] = {20, 25, 75, 90, 120, 130, 130};
        int y1Points[] = {105, 85, 80, 60, 60, 70, 105};
        GeneralPath polygon =
                new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                        x1Points.length);
        polygon.moveTo(x1Points[0], y1Points[0]);

        for (int index = 1; index < x1Points.length; index++) {
            polygon.lineTo(x1Points[index], y1Points[index]);
        }

        int x = 45;
        int y = 105;
        int R = 15;
        double kappa = 0.5522847498;
        //circle

       polygon.moveTo(x, y-R); // move to A
        polygon.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
        polygon.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
        polygon.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
        polygon.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );


        x = 105;
        y = 105;

        polygon.moveTo(x, y-R); // move to A
        polygon.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
        polygon.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
        polygon.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
        polygon.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );

        g2.setColor(Color.BLACK);
        g2.fill(polygon);
        g2.draw(polygon);

      /*  //circle1
        int x=75;
        int y=75;
        int R=60;
        double kappa = 0.5522847498;



        GeneralPath circle = new GeneralPath();
        circle.moveTo(x, y-R); // move to A
        circle.curveTo(x+R*kappa, y-R, x+R, y-R*kappa, x+R, y); // curve to A', B', B
        circle.curveTo(x+R, y+R*kappa, x+R*kappa, y+R, x, y+R );
        circle.curveTo(x-R*kappa, y+R, x-R, y+R*kappa, x-R, y);
        circle.curveTo(x-R, y-R*kappa, x-R*kappa, y-R, x, y-R );
        circle.closePath();

        g2.setColor(Color.BLACK);
        g2.fill(circle);
        g2.draw(circle); */
    }

    public Dimension getPreferredSize ()
    {
        return new Dimension ( 150   , 150 );
    }
}
