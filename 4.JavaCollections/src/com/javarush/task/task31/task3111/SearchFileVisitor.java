package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize = 0;
    private int maxSize = 0;
    private List<Path> foundFiles = new ArrayList<>();

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length
        boolean containsPartOfName = true;
        boolean containsPartOfContent = true;
        boolean minChek = true;
        boolean maxChek = true;
        if (partOfName != null && !file.getFileName().toString().contains(partOfName)){
            containsPartOfName = false;
        }
        if (partOfContent != null && !new String(content).contains(partOfContent)){
            containsPartOfContent = false;
        }
        if (minSize > content.length){
            minChek = false;
        }
        if (maxSize < content.length && maxSize != 0){
            maxChek = false;
        }

        if (containsPartOfName && containsPartOfContent && minChek && maxChek){
            foundFiles.add(file);
        }

        return FileVisitResult.CONTINUE;
    }
}
