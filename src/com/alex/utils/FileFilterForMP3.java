package com.alex.utils;

import java.io.File;

public class FileFilterForMP3 extends javax.swing.filechooser.FileFilter {

    private String fileExtension;

    public FileFilterForMP3(String fileExtension, String fileDiscription) {
        this.fileExtension = fileExtension;
        this.fileDiscription = fileDiscription;
    }

    private String fileDiscription;

    @Override
    public boolean accept(File f) {
        return f.isDirectory()||f.getAbsolutePath().endsWith(fileExtension);
    }

    @Override
    public String getDescription() {
        return (fileDiscription+" (*."+fileExtension+")");
    }
}
