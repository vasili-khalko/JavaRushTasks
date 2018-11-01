package com.javarush.task.task31.task3106;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipInputStream;


/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        String resultFileName = args[0];
        int filePartCount = args.length - 1;
        String[] fileNamePart = new String[filePartCount];
        for (int i = 0; i < filePartCount; i++) {
            fileNamePart[i] = args[i + 1];
        }
        Arrays.sort(fileNamePart);

        List<FileInputStream> fisList = new ArrayList<>();
        for (int i = 0; i < filePartCount; i++) {
            fisList.add(new FileInputStream(fileNamePart[i]));
        }
        SequenceInputStream seqInStream = new SequenceInputStream(Collections.enumeration(fisList));
        ZipInputStream zipInStream = new ZipInputStream(seqInStream);
        FileOutputStream fileOutStream = new FileOutputStream(resultFileName);
        byte[] buf = new byte[1024 * 1024];
        while (zipInStream.getNextEntry() != null) {
            int count;
            while ((count = zipInStream.read(buf)) != -1) {
                fileOutStream.write(buf, 0, count);
            }
        }
        seqInStream.close();
        zipInStream.close();
        fileOutStream.close();
        /*Set<String> files = new TreeSet<>();
        for (int i = 1; i < args.length; i++) {
            files.add(args[i]);
        }
        Path tempZip = Files.createTempFile(null, null);
        try(OutputStream outputStream = Files.newOutputStream(tempZip)) {
            for (String fnames : files) {
                try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(Paths.get(fnames)))) {
                    ZipEntry zipEntry = zipInputStream.getNextEntry();
                    while (zipEntry != null){
                        byte[] buffer = new byte[1024];
                        int count;
                        while ((count = zipInputStream.read(buffer)) > -1) {
                            outputStream.write(buffer, 0, count);
                        }
                        zipInputStream.closeEntry();
                    }

                }
            }
        }
        Files.move(tempZip, Paths.get(args[0]));*/
    }
}
