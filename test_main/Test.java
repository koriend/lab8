package test_main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test implements ActionListener{
    JFrame frame;
    JPanel pane1, pane2, pane3, cardPane;
    CardLayout card;

    public Test() {
        frame = new JFrame("CardLayout test_main.Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        pane1 = new JPanel();
        pane2 = new JPanel();
        pane3 = new JPanel();
        cardPane = new JPanel();

        pane1.setBackground(Color.BLACK);
        pane2.setBackground(Color.BLUE);
        pane3.setBackground(Color.GREEN);

        JButton button1 = new JButton("Click me to change panel");
        button1.addActionListener(this);
        pane1.add(button1);

        JButton button2 = new JButton("Click me to change panel");
        button2.addActionListener(this);
        pane2.add(button2);

        JButton button3 = new JButton("Click me to change panel");
        button3.addActionListener(this);
        pane3.add(button3);

        card = new CardLayout();

        cardPane.setLayout(card);
        cardPane.add(pane1, "First Pane");
        cardPane.add(pane2, "Second Pane");
        cardPane.add(pane3, "Third Pane");

        frame.add(cardPane);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        card.next(cardPane);
    }

    public static void main(String args[]) {
       Test test = new Test();
    }
}
