package ru.mai.gi.service.services.impl;

import org.springframework.stereotype.Service;
import ru.mai.gi.service.services.ContentService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    private String filename = "intermediate";
    final private String dirName = "src/main/resources/";
    private String curFileName = null;

    public String getCurFileName() {
        return curFileName;
    }

    public void setCurFileName(String curFileName) {
        this.curFileName = curFileName;
    }

    public String getDirName() {
        return dirName;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void saveFile(String content) throws IOException {
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
        setCurFileName(dirName + fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(file));
        bufferedWriter.write(content);
        bufferedWriter.close();
        System.out.println("File '" + file.getName() + "' was created successfully!");
    }

    @Override
    public boolean checkDir(String fileName) {
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

    @Override
    public void deleteFile() {
        String fileName = getCurFileName();
        File file = new File(fileName);
        System.out.println("File '" + file.getName() + "' deleted status: " + file.delete());
        System.out.println("");
    }
}
