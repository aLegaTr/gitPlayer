package com.alex.formMP3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForSelectedFile extends JPopupMenu {

    public MenuForSelectedFile(MP3Form form) {
        this.form = form;
        createMebuforFile();
    }

    private MP3Form form;

    private JMenuItem item1=new JMenuItem("Открыть");
    private JMenuItem item2=new JMenuItem("Стереть");
    private JMenuItem item3=new JMenuItem("Добавить");
    private JMenuItem item4=new JMenuItem("Следущая");
    private JMenuItem item5=new JMenuItem("Предыдущая");

    private void createMebuforFile(){

        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.createOpenPLS();
            }
        });
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.removeMP3();
            }
        });
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.createAddFiles();
            }
        });
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.choseNextFile();
                form.playChoseFile();
            }
        });
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.choseBeforeFile();
                form.playChoseFile();
            }
        });
        this.add(item1);
        this.add(item2);
        this.add(item3);
        this.add(item4);
        this.add(item5);
    }
  }
