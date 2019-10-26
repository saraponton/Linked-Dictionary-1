package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String alphabet="abcdefghijklmnopqrstuvwxyzé";
    static String path = "/Users/Matheus/Linked/src/com/company/unsorteddict.txt";
    static LinkedList<LinkedList> dict = new LinkedList<>();




    public static void main(String args[])
    {
        for ( char letter : alphabet.toCharArray()){
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
            for(LinkedList<String> element : dict){
                for(String word: element){
                    System.out.println(word);
                }
            }
            System.out.println(elapsedTime);


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    public static int getPosition(String word, int indicator) {
        String temp = Character.toString(word.charAt(0));
        int index = dict.get(indicator).indexOf(temp);
        if (index<0){
            index = 0;}
        for(int j=index;j< dict.get(indicator).size();j++) {
            String word_2 = (String) dict.get(indicator).get(j);
            if (word.compareTo(word_2) < 0){
                return j;
            }

        }
        return -1;
    }


}
