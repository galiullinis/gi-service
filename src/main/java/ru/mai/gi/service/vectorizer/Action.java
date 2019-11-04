package ru.mai.gi.service.vectorizer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public interface Action {

    static List<String> parseDirectory(String dirName){
        File dir = new File(dirName);
        File []arrFiles = dir.listFiles();
        List<File> lst = Arrays.asList(arrFiles);
        List<String> fileNames = new ArrayList<>();
        for (File file : lst) {
            if (file.getName().endsWith(".txt")) {
                fileNames.add(dirName + "\\" + file.getName());
            }
        }
        if (fileNames.size() == 0) {
            System.out.println("In the directory '" + dirName + "' no txt files founded!");
        };
        return fileNames;
    }

    static String createVectors(String filename) throws IOException {
        String file = filename;
        Map<VectorAttributes, String> attrMap = new HashMap<>();
        TextParser textParser = new TextParser();
        textParser.setPath(file);
        textParser.readText();

        attrMap.put(VectorAttributes.TEXT_VOLUME, String.valueOf(textParser.getTextVolume()));
        attrMap.put(VectorAttributes.LINE_LENGTH, String.valueOf(textParser.getLineLength()));
        attrMap.put(VectorAttributes.PUNCT_TO_WORDS, String.valueOf(textParser.getPunctsCount()));
        attrMap.put(VectorAttributes.RHYME, String.valueOf(textParser.getRhymeCoef()));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(attrMap.get(VectorAttributes.TEXT_VOLUME));
        stringBuilder.append(' ');
        stringBuilder.append(attrMap.get(VectorAttributes.LINE_LENGTH));
        stringBuilder.append(' ');
        stringBuilder.append(attrMap.get(VectorAttributes.PUNCT_TO_WORDS));
        stringBuilder.append(' ');
        stringBuilder.append(attrMap.get(VectorAttributes.RHYME));
        stringBuilder.append(' ');

        return String.valueOf(stringBuilder);
    }
}

