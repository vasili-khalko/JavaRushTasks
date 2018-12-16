package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        T result = null;
        ObjectMapper mapper = new ObjectMapper();
        result = mapper.readValue(new FileReader(new File(fileName)), clazz);
        return result;

    }

    public static void main(String[] args) {

    }
}
