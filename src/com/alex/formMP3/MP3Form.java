package com.alex.formMP3;

import com.alex.objects.MP3;
import com.alex.objects.PlayPleyr;
import com.alex.utils.FileFilterForMP3;
import com.alex.utils.Utils;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

public class MP3Form implements BasicPlayerListener {

    private JButton backButton;
    private JButton playButton;
    private JButton pauseButton;
    private JButton stopButton;
    private JButton aheadButton;
    private JSlider namePlaySound;
    private JSlider valueVolume;
    private JButton offSoundButton;
    private JButton downButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton uppButton;
    private JTextField textFind;
    private JButton findButton;
    private JList playList;
    private JPanel MainPanel;
    private JTextField textField1;
    private JFrame frame;


    private final String MP3_EXTENSION="mp3";
    private final String MP3_DESCRIPTION="Файлы MP3";
    private final String PLS_EXTENSION="pls";
    private final String PLS_DESCRIPTION="Плейлист";
    private final String EMTHY_STRING="";
    private final String CHOSE_FILE="Выберите файл";
    private JFileChooser fileChooser=new JFileChooser();
    private FileFilterForMP3 mp3FileFilter=new FileFilterForMP3(MP3_EXTENSION, MP3_DESCRIPTION);
    private FileFilterForMP3 plsFileFilter=new FileFilterForMP3(PLS_EXTENSION, PLS_DESCRIPTION);
    private DefaultListModel listModelPlayList=new DefaultListModel();
    private PlayPleyr pleyr=new PlayPleyr(this);
    private int Volume;

    private long duration;
    private int bytesLen;
    private long secondAmount;
    private boolean movinforJunp = false;
    private boolean moveAutomatic = false;
    private double posValue = 0.0;

    private  MenuForSelectedFile menu=new MenuForSelectedFile(this);

    public MP3Form(JFrame frame) {
        this.frame=frame;
        createAction();

    }

    private void createAction(){

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAddFiles();
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            removeMP3();
            }
        });
        uppButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            choseBeforeFile();
            }
        });
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            choseNextFile();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            playChoseFile();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            pleyr.pause();
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            pleyr.stop();
            }
        });
        aheadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            choseNextFile();
            playChoseFile();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            choseBeforeFile();
            playChoseFile();
            }
        });
        valueVolume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            pleyr.setVolume(valueVolume.getValue(), valueVolume.getMaximum());
            if (valueVolume.getValue()==0){
                offSoundButton.setSelected(true);
            }
            else offSoundButton.setSelected(false);

            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            searchMP3();
            }
        });
        offSoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(valueVolume.getValue()!=0){
                    Volume=valueVolume.getValue();
                    valueVolume.setValue(0);
                    offSoundButton.setSelected(true);
                }
                else {
                    valueVolume.setValue(Volume);
                    offSoundButton.setSelected(false);
                }
            }
        });
        textFind.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key=e.getKeyCode();
                if (key==KeyEvent.VK_ENTER){
                    searchMP3();
                }
            }
        });
        playList.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key=e.getKeyCode();
                if(key==KeyEvent.VK_ENTER){
                    playChoseFile();
                }
                if(key==KeyEvent.VK_DELETE){
                    removeMP3();
                }
            }
        });
        playList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getModifiers()==InputEvent.BUTTON1_MASK&&e.getClickCount()==2){
                    playChoseFile();
                }
                if(e.getModifiers()==InputEvent.BUTTON3_MASK&&e.getClickCount()==1){
                    menu.show(playList,e.getX(),e.getY());
                }

            }
        });
        namePlaySound.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (namePlaySound.getValueIsAdjusting()==false){
                    if (moveAutomatic==true){
                        moveAutomatic=false;
                        posValue=namePlaySound.getValue()*1.0/1000;
                        processSeek(posValue);
                    }
                    else moveAutomatic=true;
                        movinforJunp=true;
                }
        }
        });
    }

    protected void choseBeforeFile(){

        int index=playList.getSelectedIndex();
        if (index>0){
            playList.setSelectedIndex(index-1);
        }
        return;
        }

    protected boolean choseNextFile(){

        int index=playList.getSelectedIndex();
        if (index<listModelPlayList.getSize()){
            playList.setSelectedIndex(index+1);
            return true;
        }
        return false;
    }

    protected void playChoseFile(){

        int[] indexes = playList.getSelectedIndices();
        if(indexes.length>0){
            MP3 mp3 =(MP3) listModelPlayList.getElementAt(indexes[0]);// почитать
            if (mp3 instanceof MP3){
                String slenght=Utils.getfileWithoutExtension(mp3.getName());
                pleyr.play(mp3.getPath());
                pleyr.setVolume(valueVolume.getValue(), valueVolume.getMaximum());
            }
        }
    }

    private void searchMP3(){

        String text=textFind.getText();
        if(text==null||text.trim().equals(EMTHY_STRING)) return;

        ArrayList<Integer> colCheckMp3=new ArrayList<Integer>();
        for (int i=1; i<listModelPlayList.getSize(); i++){
            MP3 mp3 =(MP3) listModelPlayList.getElementAt(i);
            if(mp3.getName().toUpperCase().contains(text.toUpperCase())){
                colCheckMp3.add(i);
            }
        }
        int[] idexces=new int[colCheckMp3.size()];

        if(idexces.length==0){
        JOptionPane.showMessageDialog(MainPanel,"Поиск по строке "+text+" не дал результатов");
        findButton.requestFocus();
        return;
        }
        for (int i=0; i<idexces.length; i++){
            idexces[i]=colCheckMp3.get(i).intValue();
        }
        playList.setSelectedIndices(idexces);
    }

    protected void removeMP3(){

        int[] indices=playList.getSelectedIndices();
        if (indices.length>0){
            ArrayList<MP3> list=new ArrayList<MP3>();
            for (int i=0; i<indices.length; i++){
                MP3 mp3=(MP3)listModelPlayList.getElementAt(indices[i]);
                list.add(mp3);
            }
            for (MP3 mp3:list){
                listModelPlayList.removeElement(mp3);
            }
        }
    }

    protected void createSavePLS(){
        Utils.addFileChoser(fileChooser,plsFileFilter);
        int result=fileChooser.showSaveDialog(fileChooser);
        if(result==JFileChooser.APPROVE_OPTION){
            File selectedFiles=fileChooser.getSelectedFile();
                if(selectedFiles.exists()){
                    int res=JOptionPane.showConfirmDialog(fileChooser,"Файл уже сущесвтует, перезаписать ?","Перезапись",JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (res){
                        case JOptionPane.NO_OPTION: createSavePLS();
                        case JOptionPane.CANCEL_OPTION: fileChooser.cancelSelection();
                    }
                    fileChooser.approveSelection();
                    }
            String fileExtens=Utils.getfileExtension(selectedFiles);
            String fileNameForSave=(fileExtens!=null&&fileExtens.equals(PLS_EXTENSION))?selectedFiles.getPath():selectedFiles.getPath()+"."+PLS_EXTENSION;
            Utils.Serializable(listModelPlayList, fileNameForSave);
        }
    }

    protected void createOpenPLS(){

        Utils.addFileChoser(fileChooser,plsFileFilter);
        int result=fileChooser.showSaveDialog(fileChooser);
            if(result==JFileChooser.APPROVE_OPTION){
                File file=fileChooser.getSelectedFile();
                listModelPlayList=(DefaultListModel)Utils.deSerializable(file.getPath());
                playList.setModel(listModelPlayList);
            }

    }

    protected void createAddFiles(){
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setFileFilter(mp3FileFilter);

        int rez = fileChooser.showDialog(null,"Выберите файл");
        if(rez==JFileChooser.APPROVE_OPTION){

            File[] selectdFiles=fileChooser.getSelectedFiles();
            for (File file: selectdFiles){
                MP3 mp3 = new MP3(file.getName(), file.getPath());
                listModelPlayList.addElement(mp3);
            }
            playList.setModel(listModelPlayList);
        }
    }

    public JPanel getMainPanel() {
        return MainPanel;
    }

    private void processSeek(double bytes) {
        try {
            long skipBytes = (long) Math.round(((Integer) bytesLen).intValue() * bytes);
            pleyr.jump(skipBytes);
        } catch (Exception e) {
            e.printStackTrace();
            movinforJunp = false;
        }
    }

    @Override
    public void opened(Object o, Map map) {

        // определить длину песни и размер файла
        duration = (long) Math.round((((Long) map.get("duration")).longValue()) / 1000000);
        bytesLen = Math.round(((Integer) map.get("mp3.length.bytes")).intValue());

        // если есть mp3 тег для имени - берем его, если нет - вытаскиваем название из имени файла
        String songName = map.get("title") != null ? map.get("title").toString() : Utils.getfileWithoutExtension(new File(o.toString()).getName());

        // если длинное название - укоротить его
        if (songName.length() > 30) {
            songName = songName.substring(0, 30) + "...";
        }
        textField1.setText(songName);
    }

    @Override
    public void progress(int i, long l, byte[] bytes, Map map) {

        float progress = -1.0f;

        if ((i > 0) && (duration > 0)) {
            progress = i * 1.0f / bytesLen * 1.0f;
        }
        secondAmount = (long) (duration * progress);
        System.out.println(duration);
        if (duration != 0) {
            System.out.println(movinforJunp);
            if (movinforJunp == false) {
                namePlaySound.setValue(((int) Math.round(secondAmount * 1000 / duration)));
            }
        }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent basicPlayerEvent) {

        int state = basicPlayerEvent.getCode();
        if (state == BasicPlayerEvent.PLAYING) {
            movinforJunp = false;
        } else if (state == BasicPlayerEvent.SEEKING) {
            movinforJunp = true;
        } else if (state == BasicPlayerEvent.EOM) {
            if (choseNextFile()) {
                playChoseFile();
            }
        }

    }

    @Override
    public void setController(BasicController basicController) {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
