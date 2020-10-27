package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class ReliefButon extends JButton{
    Thing thing;
    Color color ;

    ReliefButon(Thing po, Color col){
        thing = po;
        color = col;
    }
    @Override
    public void paintComponent(Graphics g1) {

        //tringle
        Graphics2D g2 = (Graphics2D) g1;
        int x1Points[] = {20, 38, 56, 91, 130};
        int y1Points[] = {130, 55, 130, 20, 130};
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
