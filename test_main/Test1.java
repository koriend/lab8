package test_main;

import graphic.GUI;

import javax.swing.*;
import java.util.Locale;

public class Test1 {
    public static void main(String[] args){
         Locale locale_ru = new Locale("ru");
        JFrame frame = new GUI(locale_ru, "kat");
        frame.setTitle("wtf");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
