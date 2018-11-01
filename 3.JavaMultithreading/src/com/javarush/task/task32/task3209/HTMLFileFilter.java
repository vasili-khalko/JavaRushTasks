package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by Basillio on 16.02.2018.
 */
public class HTMLFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        String fileName = f.getName().toLowerCase();
        boolean result;
        if (fileName.endsWith(".html") || fileName.endsWith(".htm") || f.isDirectory()){
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы" ;
    }
}
