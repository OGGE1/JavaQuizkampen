package QuestionsAndCategories;

/**
 * Created by Benjamin Brankovic
 * Date: 2020-11-13
 * Time: 13:21
 * Project: JavaQuizkampen
 */

public enum Categories {
    SPORT("Sport och fritid"),
    DATOR("Dator och TV-spel"),
    JORDEN("Jorden runt"),
    DJUR("Djur och natur");

    String name;

    Categories(String name) {
        this.name = name;
    }

    public String getCategoriesName() {
        return name;
    }

}
