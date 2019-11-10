package com.company;
import java.io.*;
import java.util.*;
import java.util.LinkedList;

public class Main {
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    static String path = "D:\\Linked-Dictionary-1\\src\\com\\company\\unsortedDictTest2.txt";
    static String path2 = "D:\\Linked-Dictionary-1\\src\\com\\company\\sortedDictTest2.txt";
    static String path3 = "D:\\Linked-Dictionary-1\\sorted2.txt";
    static LinkedList<LinkedList> dict = new LinkedList<>();
    static File file = new File(path);
    static File file2 = new File(path2);
    static File file3 = new File(path3);


    public static void main(String args[]) throws IOException {
        long startTimeList = System.currentTimeMillis();
        createList();
        long endTimeList = System.currentTimeMillis();

        long startTimeSort = System.currentTimeMillis();
        sortFile(file);
        long endTimeSort = System.currentTimeMillis();
        System.out.println("Creating the list took:" + (endTimeList - startTimeList) + " milliseconds");
        System.out.println("Sorting took " + (endTimeSort - startTimeSort) + " milliseconds");

        long startTimeWrite = System.currentTimeMillis();
        writeFile();
        long endTimeWrite = System.currentTimeMillis();
        System.out.println("Writing the new sorted file took " + (endTimeWrite - startTimeWrite) + " milliseconds");

        console();
    }

       public static void createList(){
           for(int i = 0; i < alphabet.length(); i++){
               dict.add (new LinkedList<String>());
           }
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
           while(iterator.hasNext()) {  //i tried an if and didn't found the error in like hours until I realized it was getting out after the first iteration because
               // if is a condition and not a loop so it doesn't work with iterations

               String word2 = iterator.next().toString();
               if (word.compareTo(word2) < 0){
                   return index ;
               }
               index +=1;

           }
           return -1;

       }

       public static void writeFile() throws IOException {

           FileWriter fileWriter = new FileWriter("sorted2.txt");

           for(int i = 0; i<alphabet.length(); i++){
               ListIterator iterator = dict.get(i).listIterator(0);

               while(iterator.hasNext()){
                   fileWriter.append(iterator.next().toString());
                   fileWriter.append("\n");
               }
           }

       }

       public static void console() throws IOException {
        System.out.println("Type a number to carry out verification controls");
        Scanner scan = new Scanner (System.in);
        while(!scan.hasNextInt()){
            System.out.println("Please enter a valid number");
            scan.next();
        }
        int number = scan.nextInt();

        if (number == -1){
            verification();
        }
        else if(number < -1){
            System.out.println("Your number is too small");
        }

        else if (number > -1 && number <= 10000){

             BufferedReader b1 = null;
             BufferedReader b2 = null;
             List<String> list_file1 = new ArrayList<String>();
             List<String> list_file2 = new ArrayList<String>();
            String lineText = null;

            try {
                b1 = new BufferedReader(new FileReader(file2));
                while ((lineText = b1.readLine()) != null) {
                    list_file1.add(lineText);
                }
                b2 = new BufferedReader(new FileReader(file3));
                while ((lineText = b2.readLine()) != null) {
                    list_file2.add(lineText);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(list_file1.get(number).equalsIgnoreCase(list_file2.get(number))){
                System.out.println("Words in position " +number +" match. The word is: "+ list_file1.get(number));
            }
        }


       }


       public static void verification() throws IOException {

           BufferedReader reader1 = new BufferedReader(new FileReader(file2));
           BufferedReader reader2 = new BufferedReader(new FileReader(file3));

           boolean areEqual = true;
           int lineNum = 1;

           String line1 = reader1.readLine().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "").toLowerCase();
           String line2 = reader2.readLine().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "").toLowerCase();

           while(areEqual){
               if (line1.replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "").toLowerCase() ==
                       line2.replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "").toLowerCase()) {
                   line1 = reader1.readLine();
                   line2 = reader2.readLine();
                   lineNum++;
                   areEqual = true;
                   if(lineNum==10000){
                       System.out.println("Both files are equal");
                       areEqual = false;
                   }
               }
               else if(line1.replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "") !=
                       line2.replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "")){
                   System.out.println("Both files aren't equal");
                   areEqual = false;
               }
           }


       }

       public static void checkWord(){

       }

    }