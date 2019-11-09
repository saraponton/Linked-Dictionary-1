package com.company;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Main {
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static String path = "D:\\Workspace\\src\\com\\company\\unsorteddict.txt";
    static String path2 = "D:\\Linked-Dictionary-1\\sorted.txt";
    static LinkedList<LinkedList> dict = new LinkedList<>();
    static File file = new File(path);
    static File file2 = new File(path2);


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

        console();


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

       public static void console() throws IOException {
        System.out.println("Type -1 to carry out verification controls");
        Scanner scan = new Scanner (System.in);
        String s = scan.nextLine();

        verification();
       }


       public static void verification() throws IOException {

           BufferedReader reader1 = new BufferedReader(new FileReader(file));
           BufferedReader reader2 = new BufferedReader(new FileReader(file2));

           boolean areEqual = true;
           int lineNum = 1;

           String line1 = reader1.readLine();
           String line2 = reader2.readLine();

           while(areEqual){
               if (line1 == line2) {
                   line1 = reader1.readLine();
                   line2 = reader2.readLine();
                   lineNum++;
                   areEqual = true;
                   if(lineNum==10000){
                       System.out.println("Both files are equal");
                       areEqual = false;
                   }
               }
               else if(line1 != line2){
                   System.out.println("Both files aren't equal");
                   areEqual = false;
               }
           }


       }

       public static void checkWord(){

       }

    }