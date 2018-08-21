package com.alex.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeSkinPlayer implements ActionListener {

    private JFrame frame;
    private LookAndFeel lookAndFeel;

    public ChangeSkinPlayer(JFrame frame, LookAndFeel lookAndFeel) {
        this.frame = frame;
        this.lookAndFeel = lookAndFeel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
