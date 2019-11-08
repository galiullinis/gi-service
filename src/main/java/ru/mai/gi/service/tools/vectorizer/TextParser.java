package ru.mai.gi.service.tools.vectorizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {
    private String path;
    private float textVolume = 0;
    private float punctsCount = 0;
    private float lineLength = 0;
    private float rhymeCoef = 0;

    public TextParser(String path){
        this.path = path;
    }
    public TextParser(){}
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getTextVolume() {
        return textVolume;
    }

    public void setTextVolume(float textVolume) {
        this.textVolume = textVolume;
    }

    public float getPunctsCount() {
        return punctsCount;
    }

    public void setPunctsCount(float punctsCount) {
        this.punctsCount = punctsCount;
    }

    public float getLineLength() {
        return lineLength;
    }

    public void setLineLength(float lineLength) {
        this.lineLength = lineLength;
    }

    public float getRhymeCoef() {
        return rhymeCoef;
    }

    public void setRhymeCoef(float rhymeCoef) {
        this.rhymeCoef = rhymeCoef;
    }

    public void readText() throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s;
        String hs;
        List<Character> lastChars = new ArrayList<>();
        int linesCount = 0;
        int linesLength = 0;
        int textVolume = 0;
        int punctCount = 0;
        // normalize every line in file
        // delete punctuations
        // to get last char to find a rhyme
        while (( s = br.readLine()) != null ){
            linesCount += 1;
            punctCount += punctCount(s);
            hs = s.replaceAll("\\p{Punct}", "");
            hs = hs.replaceAll("[—»]", "");
            if (hs.length() > 0){
                if (hs.charAt(hs.length() - 1) == ' ') {
                    hs = hs.substring(0, hs.length() - 1);
                }
                lastChars.add(hs.charAt(hs.length() - 1));
                textVolume += lineVolume(hs);
                linesLength += hs.length();
            }
        }

        if (linesCount != 0){
            setLineLength(linesLength/linesCount);
            findRhyme(lastChars, linesCount);
        }
        setTextVolume(textVolume);
        setPunctsCount(punctCount);
        br.close();
    }

    private void findRhyme(List<Character> lastChars, int linesCount){
        int rhymeCount = 0;
        for (int i = 0; i < lastChars.size() - 2; i++){
            if (lastChars.get(i).equals(lastChars.get(i+1))){
                rhymeCount++;
            } else if (lastChars.get(i).equals(lastChars.get(i+2))){
                rhymeCount++;
            }
        }
        float rhymeCoef;
        rhymeCoef = (float) Math.round(((float)rhymeCount/linesCount) * 100) / 100;
        setRhymeCoef(rhymeCoef);
    }

    private int lineVolume(String line){
        int count = 0;
        count = line.split("\\s").length;
        return count;
    }

    private int punctCount(String line){
        Pattern pattern = Pattern.compile("[\\p{Punct}—]");
        Matcher matcher = pattern.matcher(line);
        int count = 0;
        while (matcher.find()){
            count++;
        }
        return count;
    }
}
