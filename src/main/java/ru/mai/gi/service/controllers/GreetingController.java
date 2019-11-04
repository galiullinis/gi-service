package ru.mai.gi.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mai.gi.service.Content;
import ru.mai.gi.service.classifier.Verse;
import ru.mai.gi.service.vectorizer.Action;

import java.io.IOException;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @PostMapping("/classify")
    public String classify(@ModelAttribute Content content, Map<String, Object> model) throws Exception {
        content.saveFile();
        String data = Action.createVectors(content.getFilename());
        content.deleteFile();
        Verse verse = new Verse();
        String textResult;
        if (verse.classifyText(data).equals("verse")){
            textResult = "Введенный вами текст является стихом!";
        } else {
            textResult = "Введенный вами текст не является стихом!";
        }
        model.put("textResult", textResult);
        return "result";
    }

}
