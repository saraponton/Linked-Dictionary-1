package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String alphabet="abcdefghijklmnopqrstuvwxyzé";
    static String path = "D:\\Workspace\\src\\com\\company\\unsorteddict.txt";
    static LinkedList<LinkedList> dict = new LinkedList<>();
    static String[] toPrint = new String[99171];



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
            int x = scan.nextInt();

            for (int j = 0;j<10;j++) {
                x = scan.nextInt();
                if (toPrint[x]== null){
                    System.out.println("-1");
                }else {
                    System.out.println(toPrint[x]);
                }

            }


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
    }


}
