package Server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-16 <br>
 * Time: 08:44 <br>
 * Project: JavaQuizkampen <br>
 */
public class Database {

    private final String[]categories = {"Djur och Natur","Sport och Fritid","Jorden runt","Data & TV-spel"};

    List<QA> category0 = new ArrayList<>(); // Djur och Natur
    List<QA> category1 = new ArrayList<>(); // Sport och Fritid
    List<QA> category2 = new ArrayList<>(); // Jorden Runt
    List<QA> category3 = new ArrayList<>(); // Data & TV-spel

    public Database(){
        loadData(1,"Sport och Fritid.txt");
    }

    public void loadData(int categoryIndex, String fileName){
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
            String question;

            while((question = in.readLine()) != null){
                String correctAnswer = in.readLine();
                String[]optionsArray = new String[4];

                for(int i = 0; i < 4; i++){
                    optionsArray[i] = in.readLine();
                }

                if(categoryIndex == 0) category0.add(new QA(question,correctAnswer,optionsArray));
                else if(categoryIndex == 1) category1.add(new QA(question,correctAnswer,optionsArray));
                else if(categoryIndex == 2) category2.add(new QA(question,correctAnswer,optionsArray));
                else category3.add(new QA(question,correctAnswer,optionsArray));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String[] getCategories(){
        return categories;
    }

    public List<QA> getQA_List(int categoryIndex){
        if(categoryIndex == 0) return category0;
        else if(categoryIndex == 1) return category1;
        else if(categoryIndex == 3) return category2;
        else return category3;
    }

    public static void main(String[] args) {
        Database d = new Database();

        for(var i : d.getCategories()){
            System.out.println(i);
        }
        System.out.println();

        for(int i = 0; i < d.getQA_List(1).size(); i++){
            System.out.println(d.getQA_List(1).get(i).getQuestion());
            System.out.println(d.getQA_List(1).get(i).getCorrectAnswer());
            for (int j = 0; j < 4; j++){
                System.out.println(d.getQA_List(1).get(i).getOptions()[j]);
            }
        }


    }
}
