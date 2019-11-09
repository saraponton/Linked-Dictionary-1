package com.company;
import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static String path = "D:\\Workspace\\src\\com\\company\\test.txt";
    static LinkedList<LinkedList> dict = new LinkedList<>();
    static File file = new File(path);



    public static void main(String args[])
    {
        for(int i = 0; i < alphabet.length(); i++){
            dict.add (new LinkedList<String>());
        }
        sortFile(file);
    }


        /*for ( char letter : alphabet.toCharArray()){
            dict.add(new LinkedList<String>());
        }
        File file = new File(path);

        try {

            Scanner sc = new Scanner(file);
            int index = 0;
            long startingTime = System.currentTimeMillis();
            while (sc.hasNextLine()) {
                String i = sc.nextLine().toLowerCase();

                int indicator = alphabet.indexOf(i.charAt(0));

                int position = getPosition(i, indicator);
                if(position != -1) {
                    dict.get(indicator).add(position, i);
                }
                else {
                    dict.get(indicator).add(i);
                }


                index++;
                System.out.println(index);
            }
            long elapsedTime = (System.currentTimeMillis() - startingTime);

            sc.close();
            PrintWriter writer = new PrintWriter("sorteddict.txt","UTF-8");
            int i =0;
            for(LinkedList<String> element : dict){
                for(String word: element){
                    toPrint[i] = word;
                    i++;
                    writer.println(word);

                }
            }
            writer.close();
            System.out.println(elapsedTime);

            Scanner scan = new Scanner(System.in);
            for (int j = 0;j<10;j++) {
                int x = scan.nextInt();
                if (toPrint[x]== null){
                }else {
                    System.out.println(toPrint[x]);

                }
                }
                scan.close();


            }
        catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }



        }

        public static int getPosition(String word, int indicator) {
        String temp = Character.toString(word.charAt(0));
        int index = dict.get(indicator).indexOf(temp);

        if (index < 0){
            index = 0;}

        for(int j = index; j < dict.get(indicator).size(); j++) {
            String word_2 = (String) dict.get(indicator).get(j);
            if (word.compareTo(word_2) < 0){
                return j;
            }

        }
        return -1;
    } */

       public static void sortFile(File x) {
           try {
               Scanner sc = new Scanner(x);

               while (sc.hasNextLine()){
                   String word = sc.nextLine().toLowerCase();
                   //compareWord(word, alphabet.indexOf(word.charAt(0)));
                   dict.get(alphabet.indexOf(word.toLowerCase().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "").charAt(0))).add(word);


                   //get's the linked list inside dict that corresponds to the position of the index within the alphabet of the first character of the word
               }

           }
           catch (FileNotFoundException e) {
               e.printStackTrace();
           }
       }

      /* public static void compareWord(String word, int indicator){

           String first_letter = Character.toString(word.charAt(0));
           int index = dict.get(indicator).indexOf(first_letter);

           while(indicator<alphabet.length() && indicator > 0){
               dict.get(indicator).add(word);
           }
           System.out.println(word + " " + indicator);
       } */








    }