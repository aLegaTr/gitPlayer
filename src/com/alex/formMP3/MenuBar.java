package com.alex.formMP3;

import com.alex.utils.ChangeSkinPlayer;
import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    private JMenu menuFile;
    private JMenu menuProporties;
    private JMenuItem itemOpen;
    private JMenuItem itemSave;
    private JMenuItem itemExit;
    private JMenuItem itemSkin1;
    private JMenuItem itemSkin2;
    private JFrame frame;
    private MP3Form form;

    public MenuBar(JFrame frame, MP3Form form) {
        this.frame = frame;
        createMenuBar();
        this.form=form;
    }

    public void createMenuBar(){

        itemOpen=new JMenuItem("Открыть");
        //itemOpen.setIcon(new ImageIcon(getClass().getResource("/icon/open-icon.png")));
        itemSave=new JMenuItem("Cохранить");
        //itemSave.setIcon(new ImageIcon(getClass().getResource("/icon/save_16.png")));
        itemExit=new JMenuItem("Выйти");
        //itemExit.setIcon(new ImageIcon(getClass().getResource("/icon/exit.png")));
        itemSkin1=new JMenuItem("Скин 1");
        itemSkin2=new JMenuItem("Скин 2");
        addActionItem();
        menuFile=new JMenu("Файл");
        menuFile.add(itemOpen);
        menuFile.add(itemSave);
        menuFile.add(itemExit);
        menuProporties=new JMenu("Свойства");
        menuProporties.add(itemSkin1);
        menuProporties.add(itemSkin2);
        super.add(menuFile);
        super.add(menuProporties);
    }

    public void addActionItem(){

        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.createOpenPLS();
            }
        });

        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                form.createSavePLS();
            }
        });

        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        itemSkin1.addActionListener(new ChangeSkinPlayer(frame,new GraphiteLookAndFeel()));
        itemSkin2.addActionListener(new ChangeSkinPlayer(frame, new SmartLookAndFeel()));
    }

}
