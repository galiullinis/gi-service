package ru.mai.gi.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mai.gi.service.domain.Content;
import ru.mai.gi.service.services.Classification;
import ru.mai.gi.service.services.ContentService;
import ru.mai.gi.service.services.impl.ClassificationImpl;
import ru.mai.gi.service.tools.classifier.Verse;
import ru.mai.gi.service.tools.vectorizer.Action;

import java.util.*;

@Controller
public class GreetingController {

    @Autowired
    private ContentService contentService;


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @PostMapping("/classify")
    public String classify(@ModelAttribute Content content, Map<String, Object> model) throws Exception {
        contentService.saveFile(content.getContent());
        String data = Action.createVectors(contentService.getCurFileName());
        contentService.deleteFile();
        Verse verse = new Verse();
        String textResult = verse.classifyText(data);
        Classification classification = new ClassificationImpl(textResult);
        textResult = classification.getResult(textResult);
        model.put("textResult", textResult);
        return "result";
    }

}
