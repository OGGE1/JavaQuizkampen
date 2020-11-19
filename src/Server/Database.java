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

    private int nrOfQuestions = 3;

    private final String[]categories = {"Djur & natur","Sport & fritid","Jorden runt","Data- & TVspel"};

    List<QA> category0 = new ArrayList<>(); // Djur & natur.txt
    List<QA> category1 = new ArrayList<>(); // Sport & fritid.txt
    List<QA> category2 = new ArrayList<>(); // Jorden runt.txt
    List<QA> category3 = new ArrayList<>(); // Data- & TVspel.txt

    public Database(){
        loadData(0,"Djur & natur.txt");
        loadData(1,"Sport & fritid.txt");
        loadData(2,"Jorden runt.txt");
        loadData(3,"Data- & TVspel.txt");
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

    public List<QA> get3Questions(String category){
        int i;
        for(i = 0; i < 4; i++){
            if(category.equalsIgnoreCase(categories[i])) break;
        }

        List<QA> list = get3Questions(i);
        return list;
    }

    public List<QA> get3Questions(int category){
        List<QA> list = new ArrayList<>();
        for(int i = 0; i < nrOfQuestions; i++){
            if(category == 0) list.add(category0.remove(0));
            else if(category == 1) list.add(category1.remove(0));
            else if(category == 2) list.add(category2.remove(0));
            else list.add(category3.remove(0));
        }
        return list;
    }

    public String[] getCategories(){
        return categories;
    }

    public List<QA> getQA_List(int categoryIndex){  // Denna metod behövs troligen inte
        if(categoryIndex == 0) return category0;
        else if(categoryIndex == 1) return category1;
        else if(categoryIndex == 3) return category2;
        else return category3;
    }






    public static void main(String[] args) {    // För testsyften
        Database d = new Database();

        for(var i : d.getCategories()){
            System.out.println(i);
        }
        System.out.println();

        List<QA> list = d.get3Questions("Djur & natur");

        for(int i = 0; i < 3; i++){
            System.out.println(list.get(i).getQuestion());
        }

        list = d.get3Questions("Djur & natur");

        for(int i = 0; i < 3; i++){
            System.out.println(list.get(i).getQuestion());
        }

    }
}
