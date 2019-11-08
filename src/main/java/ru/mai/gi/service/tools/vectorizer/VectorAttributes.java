package ru.mai.gi.service.tools.vectorizer;

public enum VectorAttributes {
    LINE_LENGTH("line_length"), // средняя длина одной строки
    PUNCT_TO_WORDS("punctuations_to_words"), // отношение количества знаков препинания к количеству слов
    TEXT_VOLUME("text_volume"), // объем текста - количество слов
    RHYME("rhyme"); // рифма - отношение строк с одинаковыми окончаниями к количеству строк

    private String code;

    VectorAttributes(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
