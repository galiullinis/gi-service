package ru.mai.gi.service.services;

import java.io.IOException;

public interface ContentService {
    String getFilename();
    void saveFile(String content) throws IOException;
    boolean checkDir(String fileName);
    void deleteFile();
}
