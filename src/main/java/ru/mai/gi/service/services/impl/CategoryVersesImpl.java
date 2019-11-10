package ru.mai.gi.service.services.impl;

import ru.mai.gi.service.services.CategoryVerses;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryVersesImpl implements CategoryVerses {

    private String author;

    public CategoryVersesImpl(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public List<String> showVerses() {
        try {
            List<String> templateFileNames = new ArrayList<>();
            List<String> categories = new ArrayList<>();
            if (author.equals("pushkin")) {
                categories.add("Стихи Александра Пушкина");
            } else if (author.equals("hayam")){
                categories.add("Стихи Омара Хаяма");
            } else if (author.equals("krilov")){
                categories.add("Басни Ивана Крылова");
            } else if (author.equals("ahmadullina")){
                categories.add("Стихи Беллы Ахмадуллиной");
            } else if (author.equals("mayakovskiy")){
                categories.add("Стихи Владимира Маяковского");
            } else if (author.equals("marshak")){
                categories.add("Стихи Самуила Маршака");
            } else if (author.equals("all")) {
                categories.add("Стихи Александра Пушкина");
                categories.add("Стихи Омара Хаяма");
                categories.add("Басни Ивана Крылова");
                categories.add("Стихи Беллы Ахмадуллиной");
                categories.add("Стихи Владимира Маяковского");
                categories.add("Стихи Самуила Маршака");
            }
            for (String category : categories){
                String path = "src/main/resources/verses/" + category;
                File dir = new File(path);
                File[] arrFiles = dir.listFiles();
                List<File> lst = Arrays.asList(arrFiles);
                List<String> fileNames = new ArrayList<>();
                for (File file : lst) {
                    templateFileNames.add(file.getName());
                }
            }
            return templateFileNames;
        } catch (Exception e) {
            System.out.println("Wrong directory! Please, try again..");
            List<String> error = new ArrayList<>();
            error.add("Error: can't find a valid category");
            return error;
        }
    }
}
