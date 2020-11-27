package Server;

import java.io.*;
import java.util.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2020-11-16 <br>
 * Time: 08:44 <br>
 * Project: JavaQuizkampen <br>
 */
public class Database {

    private int nrOfQuestions; // Läses in från properties fil som sätter antalet frågor per runda

    Properties properties = new Properties();

    private final String[]categories = {"Djur & natur","Sport & fritid","Jorden runt","Data- & TVspel"};

    List<QA> category0 = new ArrayList<>(); // Djur & natur.txt
    List<QA> category1 = new ArrayList<>(); // Sport & fritid.txt
    List<QA> category2 = new ArrayList<>(); // Jorden runt.txt
    List<QA> category3 = new ArrayList<>(); // Data- & TVspel.txt

    public Database(){
        setSettings();
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

    public List<QA> getQuestions(String category){
        int i;
        for(i = 0; i < 4; i++){
            if(category.equalsIgnoreCase(categories[i])) break;
        }
        List<QA> list = getQuestions(i);
        return list;
    }

    private List<QA> getQuestions(int categoryIndex){
        Random rand = new Random();
        List<QA> list = new ArrayList<>();
        for(int i = 0; i < nrOfQuestions; i++){
            if(categoryIndex == 0) {
                int x = rand.nextInt(category0.size());
                list.add(category0.remove(x));
            }
            else if(categoryIndex == 1) {
                int x = rand.nextInt(category1.size());
                list.add(category1.remove(x));
            }
            else if(categoryIndex == 2) {
                int x = rand.nextInt(category2.size());
                list.add(category2.remove(x));
            }
            else {
                int x = rand.nextInt(category3.size());
                list.add(category3.remove(x));
            }
        }
        return list;
    }

    public void setSettings() {
        try {
            properties.load(new FileInputStream("src/Properties.properties"));
        } catch (Exception e) {
            System.out.println("Could not load properties file");
            e.printStackTrace();
        }
        nrOfQuestions = Integer.parseInt(properties.getProperty("questionsPerRound", "3"));
    }

}
