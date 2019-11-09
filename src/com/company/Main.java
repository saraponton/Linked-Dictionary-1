package com.company;
import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
import java.util.LinkedList;

public class Main {
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static String path = "D:\\Workspace\\src\\com\\company\\unsorteddict.txt";
    static LinkedList<LinkedList> dict = new LinkedList<>();
    static File file = new File(path);


    public static void main(String args[]) throws IOException {
        for(int i = 0; i < alphabet.length(); i++){
            dict.add (new LinkedList<String>());
        }

        long startTimeSort = System.currentTimeMillis();
        sortFile(file);
        long endTimeSort = System.currentTimeMillis();
        System.out.println("Sorting took " + (endTimeSort - startTimeSort) + " milliseconds");

        long startTimeWrite = System.currentTimeMillis();
        writeFile();
        long endTimeWrite = System.currentTimeMillis();
        System.out.println("Writing the new sorted file took " + (endTimeWrite - startTimeWrite) + " milliseconds");


    }


       public static void sortFile(File x) {
           try {
               Scanner sc = new Scanner(x);
               int index = 0;
               while (sc.hasNextLine()){
                   String word = sc.nextLine().toLowerCase().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "");
                   int indicator = alphabet.indexOf(word.charAt(0));
                   int location = compareWord(word, indicator);

                   if (location !=-1) {

                       dict.get(indicator).add(location, word); //se añade la palabra en la posicion en la que se compara con la otra y es más pequeña

                   }
                   else {
                       dict.get(indicator).add(word); //se añade al final si la palabra es la más grande
                   }
                index++;
                   System.out.println(index);
               }

           }
           catch (FileNotFoundException e) {
               e.printStackTrace();
           }
       }

       public static int compareWord(String word, int indicator){

           ListIterator iterator = dict.get(indicator).listIterator(0); //usa clase list iterator ya que es mas rapida para comparar elementos que con un for loop
            int index = 0;
           while(iterator.hasNext()) {  //i tried an if and didn't found the error in like hours until I realized it was getting out after the first iteration because if loops dont work with iterations

               String word2 = iterator.next().toString();
               if (word.compareTo(word2) < 0){
                   return index ;
               }
               index +=1;

           }
           return -1;

       }

       public static void writeFile() throws IOException {
           FileWriter fileWriter = new FileWriter("sorted.txt");
           for(int i = 0; i<alphabet.length(); i++){
               ListIterator iterator = dict.get(i).listIterator(0);
               while(iterator.hasNext()){
                   fileWriter.append(iterator.next().toString());
                   fileWriter.append("\n");
               }
           }

       }

       public static void search(){
        Scanner scan = new Scanner (System.in);

       }
        /*
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
 */







    }