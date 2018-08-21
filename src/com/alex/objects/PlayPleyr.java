package com.alex.objects;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

import java.io.File;

public class PlayPleyr extends BasicPlayer {

    private BasicPlayer player=new BasicPlayer();
    private String currentFileName;
    private double currentValueVolume;

    public PlayPleyr(BasicPlayerListener listener) {
        player.addBasicPlayerListener(listener);
    }

    public void play(String fileName){

            try {
                if(currentFileName!=null&&currentFileName.equals(fileName)&&player.getStatus()==BasicPlayer.PAUSED){
                player.resume();
                return;
                }
                currentFileName=fileName;
                player.open(new File(fileName));
                player.play();
                player.setGain(currentValueVolume);

            } catch (BasicPlayerException e) {
                e.printStackTrace();
            }
    }

    public void stop(){
        try {
            player.stop();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        try {
            player.pause();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    public void setVolume(int currentVolume, int maxVolume){
        try {
            currentValueVolume=currentVolume;
            if(currentValueVolume==0){
                player.setGain(0);
            }
            else player.setGain(calcVolume(currentVolume, maxVolume));

        } catch (BasicPlayerException e) {
                e.printStackTrace();
            }
    }

    public double calcVolume(int currentVolume, int maxVolume){
        currentValueVolume=(double)currentVolume/(double)maxVolume;
        return currentValueVolume;
    }

    public void jump(long bytes){
        try {
            player.seek(bytes);
            player.setGain(currentValueVolume);
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }
    }
}
