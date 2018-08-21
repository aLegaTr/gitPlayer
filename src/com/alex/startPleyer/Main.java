package com.alex.startPleyer;

import com.alex.formMP3.MP3Form;
import com.alex.formMP3.MenuBar;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import javax.swing.*;

public class Main {

      public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new GraphiteLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame frame=new JFrame("PlayerMP3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(370,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
       //frame.setIconImage(new Icon().getClass().getResource("/icon/formMP3.png"));

        MP3Form form=new MP3Form(frame);
        MenuBar menuBar=new MenuBar(frame, form);
        frame.setJMenuBar(menuBar);

        frame.add(form.getMainPanel());

        frame.setVisible(true);
    }
}
