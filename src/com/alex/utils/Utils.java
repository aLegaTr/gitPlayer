package com.alex.utils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;

public class Utils implements Serializable {

    public static String getfileWithoutExtension(String fileName){

        int index=fileName.lastIndexOf(".");
        if(index>0&&index<=fileName.length()-2){
            return fileName.substring(0, index);
        }
        return "file no name";
    }

    public static String getfileExtension(File file){

        String ext=null;
        int index=file.getName().lastIndexOf(".");
        if(index>0&&index<file.getName().length()-1){
           ext=file.getName().substring(index+1).toLowerCase();
        }
        return ext;
    }

    public static void addFileChoser(JFileChooser fileChooser, FileFilter fileFilter){

        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
        fileChooser.setFileFilter(fileFilter);
        fileChooser.setSelectedFile(new File(""));
    }

    public static void Serializable(Object object, String fileName){
        try {
            FileOutputStream fos=new FileOutputStream(fileName);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            oos.close();

            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }
        }

    public static Object deSerializable(String fileName){

        try {

            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois=new ObjectInputStream(fis);
            Object obj=ois.readObject();
            fis.close();
            ois.close();
            return obj;

        } catch (IOException e) {
             e.printStackTrace();
        } catch (ClassNotFoundException e) {
             e.printStackTrace();
        }
        return null;
    }
}
