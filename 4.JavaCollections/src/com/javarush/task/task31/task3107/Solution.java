package com.javarush.task.task31.task3107;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
Null Object Pattern
*/
public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {
        try {
            boolean hiden = Files.isHidden(Paths.get(pathToFile));
            boolean executable = Files.isExecutable(Paths.get(pathToFile));
            boolean directory = Files.isDirectory(Paths.get(pathToFile));
            boolean writable = Files.isWritable(Paths.get(pathToFile));
            fileData = new ConcreteFileData(hiden, executable, directory, writable);
        } catch (FileNotFoundException e) {
            fileData = new NullFileData(e);
        } catch (IOException e) {
            fileData = new NullFileData(e);
        }
    }

    public FileData getFileData() {
        return fileData;
    }
}
