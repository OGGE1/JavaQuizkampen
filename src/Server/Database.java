package Server;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-16 <br>
 * Time: 08:44 <br>
 * Project: JavaQuizkampen <br>
 */
public class Database {

    private String[]categories = {"Sport och Fritid","Jorden runt","Djur och Natur","Data & TV-spel"};

    List<QA> category0 = new ArrayList<>();
    List<QA> category1 = new ArrayList<>();
    List<QA> category2 = new ArrayList<>();
    List<QA> category3 = new ArrayList<>();

    public Database(){
        loadData(0,"Sport och Fritid.txt");
    }

    public void loadData(int categoryIndex, String fileName){
        try(BufferedReader in = new BufferedReader(new FileReader(fileName))){
            String question;

            while((question = in.readLine()) != null){
                String correctAnswer = in.readLine();
                String option0 = in.readLine();
                String option1 = in.readLine();
                String option2 = in.readLine();
                String option3 = in.readLine();
                String[]optionsArray = {option0,option1,option2,option3};

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

        for(int i = 0; i < d.category0.size(); i++){
            System.out.println(d.category0.get(i).getQuestion());
            System.out.println(d.category0.get(i).getCorrectAnswer());
            for (int j = 0; j < 4; j++){
                System.out.println(d.category0.get(i).getOptions()[j]);
            }
        }


    }
}
