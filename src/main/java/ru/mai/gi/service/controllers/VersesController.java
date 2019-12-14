package ru.mai.gi.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mai.gi.service.services.CategoryVerses;
import ru.mai.gi.service.services.impl.CategoryVersesImpl;

import java.io.*;
import java.util.*;

@RequestMapping("verses")
@Controller
public class VersesController {

    private final Map<String, String> categories = new HashMap<String, String>() {{
        put("all", "Все стихи");
        put("pushkin", "Стихи Александра Пушкина");
        put("hayam", "Стихи Омара Хаяма");
        put("krilov", "Басни Ивана Крылова");
        put("ahmadullina", "Стихи Беллы Ахмадуллиной");
        put("mayakovskiy", "Стихи Владимира Маяковского");
        put("marshak", "Стихи Самуила Маршака");
    }};

    @GetMapping()
    public String categories(Map<String, Object> model){
        Set<Map.Entry<String, String>> entrySet = categories.entrySet();
        model.put("categories", entrySet);
        return "verses";
    }

    @GetMapping("{author}")
    public String categoryVerses(@PathVariable("author") String author, Map<String, Object> model) {
        CategoryVerses categoryVerses = new CategoryVersesImpl(author);
        List<String> templateVersesNames = categoryVerses.showVerses();
        int count;
        count = templateVersesNames.size();
        model.put("count", count);
        model.put("category", author);
        model.put("verses", templateVersesNames);
        return "authVerses";
    }

    @GetMapping("{author}/{verse}")
    public String verse(@PathVariable Map<String, String> pathVars,
                        Map<String, Object> model) throws IOException {
        File file = new File("src/main/resources/verses/" +
                categories.get(pathVars.get("author")) + "/" + pathVars.get("verse"));
        BufferedReader bf = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();
        String s = null;
        while ((s = bf.readLine()) != null) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        model.put("verse", stringBuilder);
        model.put("verseName", pathVars.get("verse"));
        model.put("category", pathVars.get("author"));
        return "verse";
    }
}
