package Server;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-16 <br>
 * Time: 08:44 <br>
 * Project: JavaQuizkampen <br>
 */
public class Database {

    String[]categories = {"Sport och Fritid","Jorden runt","Djur och Natur","Data & TV-spel"};

    List<QA> category1 = new ArrayList<>();
    List<QA> category2 = new ArrayList<>();
    List<QA> category3 = new ArrayList<>();
    List<QA> category4 = new ArrayList<>();


    public static void main(String[] args) {
        Database d = new Database();

        String[] array = {"Kalle","John","Anders","Viktor"};

        d.category1.add(new QA("Vad heter du?","John",array));

        System.out.println("Kategorier***************");
        for(int i = 0; i < 4; i++){
            System.out.println(d.categories[i]);
        }

        System.out.println("Fråga: " + d.category1.get(0).getQuestion());
        System.out.println("Svar:  " + d.category1.get(0).getCorrectAnswer());

        for(int i = 0; i < 4; i++){
            System.out.println(d.category1.get(0).getOptions()[i]);
        }
    }
}
