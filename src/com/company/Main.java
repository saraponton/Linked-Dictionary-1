package com.company;
import java.io.*;
import java.text.Normalizer;
import java.util.*;
import java.util.LinkedList;

public class Main {
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    static String unsortedPath = "/Users/saraponton/git_lab/LinkedDictCodingPrinciples/src/com/company/unsortedDictTest2.txt";
    static String alreadySortedPath = "/Users/saraponton/git_lab/LinkedDictCodingPrinciples/src/com/company/sortedDictTest2.txt";
    static String newSortedPath = "/Users/saraponton/git_lab/LinkedDictCodingPrinciples/sorted2.txt";

    static LinkedList<LinkedList> dict = new LinkedList<>();

    static File unsortedFile = new File(unsortedPath);
    static File alreadySortedFile = new File(alreadySortedPath);
    static File newSortedFile = new File(newSortedPath);


    public static void main(String args[]) throws IOException {

        long[] timers = new long[3];

        long startTimeCreateList = System.currentTimeMillis();
        createLinkedLists();
        long endTimeCreateList = System.currentTimeMillis();
        long createList = (endTimeCreateList - startTimeCreateList);

        long startTimeSort = System.currentTimeMillis();
        sortFile(unsortedFile);
        long endTimeSort = System.currentTimeMillis();
        long sortTime = (endTimeSort - startTimeSort);

        System.out.println("Creating the list took: " + createList + " milliseconds");
        System.out.println("Sorting took " + sortTime + " milliseconds");

        long startTimeWrite = System.currentTimeMillis();
        writeSortedFile();
        long endTimeWrite = System.currentTimeMillis();
        long timeWrite = endTimeWrite - startTimeWrite;
        System.out.println("Writing the new sorted file took " + timeWrite + " milliseconds");

        doAverage(createList, sortTime, timeWrite);

        timers[0] = endTimeCreateList - startTimeCreateList;
        timers[1] = endTimeSort - startTimeSort;
        timers[2] = endTimeWrite - startTimeWrite;

        double maxTime = Double.MIN_VALUE;

        for (int i = 0; i<timers.length; i++){
            if(timers[i]>maxTime){
                maxTime = timers[i];
            }
        }
        System.out.println("The longest task took: "+maxTime +" milliseconds");


        console();
    }

       public static void createLinkedLists(){
           for(int i = 0; i < alphabet.length(); i++){
               dict.add (new LinkedList<String>());
               //Creates a linked list with the same length of the alphabet. Inside that Linked List there will be other Linked Lists where where each word will be stored corresponding to the index of that word.
           }
       }

       public static void sortFile(File file) {
           try {
               Scanner sc = new Scanner(file);
               int index = 0;
               while (sc.hasNextLine()){
                   String word = sc.nextLine().toLowerCase().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "");
                   int indicator = alphabet.indexOf(word.charAt(0));
                   int location = compareWordinPosition(word, indicator);
                    //Each word will run through this code where it will be stored before the previous word if it's "smaller" or after if it's "bigger".
                   if (location !=-1) {

                       dict.get(indicator).add(location, word);

                   }
                   else {
                       dict.get(indicator).add(word);
                   }
                index++;
                   System.out.println(index);
               }

           }
           catch (FileNotFoundException e) {
               e.printStackTrace();
           }
       }

       public static int compareWordinPosition(String word, int indicator){

           ListIterator iterator = dict.get(indicator).listIterator(0); //We changed our code as we found out it is better to use a Class List Iterator than a for loop as it makes the program run much faster.
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

       public static void writeSortedFile() throws IOException { //In this part of the code we used the FileWriter class to write the new document
        // called sorted2.txt. Also, we changed the for loop for a ListIterator to make it faster. Finally, we separate the code so that each word will be printed in a different line.

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
        System.out.println("Type a number to carry out verification controls. To exit type -2 ");
        boolean flag = true;
        Scanner scan = new Scanner (System.in);
        int linesinFile = countLinesinFile();

        while (flag) {
               while (!scan.hasNextInt()) {
                   System.out.println("Please enter a valid number between -2 & " + linesinFile);
                   scan.next();
               }
               int inputNumber = scan.nextInt();
                if (inputNumber ==-2){
                    flag = false;
                }
               if (inputNumber == -1) {
                   verification();
               }

               else if (inputNumber < -2 || inputNumber > linesinFile) {
                   System.out.println("Your number is outside of range");
               }

               else if (inputNumber > -1 && inputNumber <= linesinFile) {
                  // The program will read each line from both files and store each word in an arraylist. After, it will compare each word to the position they hold in that arraylist.
                   BufferedReader b1 = null;
                   BufferedReader b2 = null;
                   List<String> list_file1 = new ArrayList<String>();
                   List<String> list_file2 = new ArrayList<String>();
                   String lineText = null;

                   try {
                       b1 = new BufferedReader(new FileReader(newSortedFile));
                       while ((lineText = b1.readLine()) != null) {
                           list_file1.add(lineText);
                       }
                       b2 = new BufferedReader(new FileReader(alreadySortedFile));
                       while ((lineText = b2.readLine()) != null) {
                           list_file2.add(lineText);
                       }
                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }

                   if (list_file1.get(inputNumber).equalsIgnoreCase(list_file2.get(inputNumber))) {  //The program verifies if the information in both files is the same
                       System.out.println("Words in position " + inputNumber + " match. The word is: " + list_file1.get(inputNumber));
                   }
               }

           }
       }


    public static void verification() throws IOException {
        BufferedReader b1 = null;
        BufferedReader b2 = null;
        List<String> list_file1 = new ArrayList<String>();
        List<String> list_file2 = new ArrayList<String>();
        String lineText = null;
        int count = 0;
        boolean flag = true;

        try {
            b1 = new BufferedReader(new FileReader(newSortedFile));
            while ((lineText = b1.readLine()) != null) {
                list_file1.add(lineText);
            }
            b2 = new BufferedReader(new FileReader(alreadySortedFile));
            while ((lineText = b2.readLine()) != null) {
                list_file2.add(lineText);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (flag) {
            if(list_file1.get(count).equalsIgnoreCase(list_file2.get(count)) && count <= countLinesinFile()){
                count++; //in 1 file there are less words than in the other
                System.out.println(count);
                System.out.println(countLinesinFile());
            }
            if(count == countLinesinFile()){
                System.out.println("Both files contain the same words");
                flag = false;
            }
        }


    }

       public static double doAverage(long a, long b, long c){
        System.out.println("Average time of execution was: " +((a+b+c)/3) +" milliseconds");
        return (a+b+c) / 3;
        //Finally, the program calculates the average of the 3 parameters previously stored.
       }


    public static int countLinesinFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(alreadySortedFile));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }

    }

