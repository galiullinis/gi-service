package ru.mai.gi.service;

import java.io.*;

public class Content {
    private String content;

    public String getContent() throws IOException {
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void saveFile() throws IOException {
        String fileName = "src/main/resources/intermediate.txt";
        File file = new File(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
        file.delete();
    }
}
