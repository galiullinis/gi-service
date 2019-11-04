package ru.mai.gi.service;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Content {
    private String content;
    public String filename = "intermediate";
    final private String dirName = "src/main/resources/";

    public String getDirName() {
        return dirName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() throws IOException {
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void saveFile() throws IOException {
        int count = 1;
        boolean checker;
        String fileName = filename + "(" + String.valueOf(count) + ").txt";
        checker = checkDir(fileName);
        if (!checker){
            while (!checker){
                count++;
                fileName = filename + "(" + String.valueOf(count) + ").txt";
                checker = checkDir(fileName);
            }
        }
        File file = new File(dirName + fileName);
        setFilename(dirName + fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
        System.out.println("File '" + file.getName() + "' was created successfully!");
    }

    private boolean checkDir(String fileName) {
        boolean result = true;
        File dir = new File(dirName);
        File[] arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        for (File file : lst) {
            if (file.getName().equals(fileName)) {
                result = false;
            }
        }
        return result;
    }

    public void deleteFile() {
        String fileName = getFilename();
        File file = new File(fileName);
        System.out.println("File '" + file.getName() + "' deleted status: " + file.delete());
        System.out.println("");
    }
}
