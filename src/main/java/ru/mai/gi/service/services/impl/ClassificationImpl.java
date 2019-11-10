package ru.mai.gi.service.services.impl;

import ru.mai.gi.service.services.Classification;

public class ClassificationImpl implements Classification {

    private String classification;

    public ClassificationImpl(String classification) {
        this.classification = classification;
    }

    @Override
    public String getResult(String classification) {
        String textResult;
        if (classification.equals("verse")){
            textResult = "Введенный вами текст является стихом!";
        } else {
            textResult = "Введенный вами текст не является стихом!";
        }
        return textResult;
    }
}
