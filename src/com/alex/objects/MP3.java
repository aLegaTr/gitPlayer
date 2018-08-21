package com.alex.objects;

import com.alex.utils.Utils;

import java.io.Serializable;

public class MP3 implements Serializable {

    private String name;
    private String path;

    public MP3(String name, String path) {
        this.name = name;
        this.path = path;
    }

    @Override
    public boolean equals(Object obj) {
       if(!(obj instanceof MP3)) return false;
       else {
           MP3 mp3=(MP3)obj;
           return path.equals(mp3.getPath());
       }
    }

    @Override
    public String toString(){
        return Utils.getfileWithoutExtension(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
