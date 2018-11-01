package com.javarush.task.task31.task3101;
import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) throws IOException{
        File dir = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        File dest = new File(resultFileAbsolutePath.getParent()+ "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, dest);
        final ArrayList<File> list = new ArrayList<>();
        Files.walkFileTree(dir.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toFile().length() > 50) FileUtils.deleteFile(file.toFile());
                else list.add(file.toFile());
                return FileVisitResult.CONTINUE;
            }
        });
        Collections.sort(list, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (File file : list) {
            try(FileReader reader = new FileReader(file); FileOutputStream writer = new FileOutputStream(dest)) {
                while (reader.ready()) writer.write(reader.read());
                reader.close();
                writer.write("\n".getBytes());
            }
        }
    }
    public static void deleteFile(File file) {
        if (!file.delete()) System.out.println("Can not delete file with name " + file.getName());
    }
}