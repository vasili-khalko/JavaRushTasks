package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        Path start = Paths.get(root);
        List<String> fileList = new ArrayList<>();
        Files.walkFileTree(start, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (!attrs.isDirectory()){
                    fileList.add(file.toAbsolutePath().toString());
                }
                return FileVisitResult.CONTINUE;
            }
        });
        return fileList;

    }

    public static void main(String[] args) throws IOException {
        List<String> fileList = getFileTree("d:\\tmp");
        for (String filePath : fileList)
            System.out.println(filePath);
    }
}
