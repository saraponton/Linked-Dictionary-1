package com.company;
import java.io.*;
import java.lang.reflect.Array;
import java.text.Normalizer;
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

        long[] timers = new long[3];

        long startTimeCreateList = System.currentTimeMillis();
        createList();
        long endTimeCreateList = System.currentTimeMillis();
        long createList = (endTimeCreateList - startTimeCreateList);

        long startTimeSort = System.currentTimeMillis();
        sortFile(file);
        long endTimeSort = System.currentTimeMillis();
        long sortTime = (endTimeSort - startTimeSort);

        System.out.println("Creating the list took: " + createList + " milliseconds");
        System.out.println("Sorting took " + sortTime + " milliseconds");

        long startTimeWrite = System.currentTimeMillis();
        writeFile();
        long endTimeWrite = System.currentTimeMillis();
        long timeWrite = endTimeWrite - startTimeWrite;
        System.out.println("Writing the new sorted file took " + timeWrite + " milliseconds");

        doAverage(createList, sortTime, timeWrite);

        timers[0] = endTimeCreateList - startTimeCreateList;
        timers[1] = endTimeSort - startTimeSort;
        timers[2] = endTimeWrite - startTimeWrite;

        double max = Double.MIN_VALUE;

        for (int i = 0; i<timers.length; i++){
            if(timers[i]>max){
                max = timers[i];
            }
        }
        System.out.println("The longest task took: "+max +" milliseconds");


        console();
    }

       public static void createList(){
           for(int i = 0; i < alphabet.length(); i++){
               dict.add (new LinkedList<String>());
               //crea una linked list del tamaño del abecedario con linked lists dentro donde ira cada palabra correspondiente al index de la letra
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

           FileWriter fileWriter = new FileWriter("sorted2.txt"); //clase filewriter para esribir el nuevo documento llamado "sorted2.txt"

           for(int i = 0; i<alphabet.length(); i++){
               ListIterator iterator = dict.get(i).listIterator(0); //recorre la linked list interior de la letra con el iterator empezando
               // desde cero para ir más rapido que con un for

               while(iterator.hasNext()){
                   fileWriter.append(iterator.next().toString());
                   fileWriter.append("\n"); //separa el contenido en lineas (creo que aqui puede estar el error de que no compare bien los 2 files
               }
           }

       }

       public static void console() throws IOException {
        System.out.println("Type a number to carry out verification controls. -2 = exit. ");
        boolean flag = true;
        Scanner scan = new Scanner (System.in);

        while (flag) {
               while (!scan.hasNextInt()) {
                   System.out.println("Please enter a valid number between -2 & 9457");
                   scan.next();
               }
               int number = scan.nextInt();
                if (number ==-2){
                    flag = false;
                }
               if (number == -1) {
                   verification();
               }

               else if (number < -2 || number > 9457) {
                   System.out.println("Your number is outside of range");
               }

               else if (number > -1 && number <= 9457) {
                    //vamos a leer ambos files linea por linea guardando cada linea en un arraylist para luego poder comparar las palabras en cada posicion
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

                   if (list_file1.get(number).equalsIgnoreCase(list_file2.get(number))) {   //aqui se compara
                       System.out.println("Words in position " + number + " match. The word is: " + list_file1.get(number));
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

           try {
               b1 = new BufferedReader(new FileReader(file2));
               while ((lineText = b1.readLine()) != null) {
                   String normalized_string = Normalizer.normalize(lineText, Normalizer.Form.NFD);
                   list_file1.add(normalized_string.toLowerCase());
               }
               b2 = new BufferedReader(new FileReader(file3));
               while ((lineText = b2.readLine()) != null) {
                   String normalized_string = Normalizer.normalize(lineText, Normalizer.Form.NFD);
                   list_file2.add(normalized_string.toLowerCase());
               }
           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
//verifica si los arrays con la informacion de ambos documentos son iguales. Siempre sale content mismatch (puede ser por algun caracter especial)
                   if(list_file1.equals(list_file2)){
                       System.out.println("The files contain the same information ");

                   }

                   else{
                       System.out.println("Content mismatch in both files");
                   }

       }

       public static double doAverage(long a, long b, long c){
        System.out.println("Average time of execution was: " +((a+b+c)/3) +" milliseconds");
        return (a+b+c) / 3;
        //calcula el average de 3 parametros pasados (serán los time taken en el main)
       }

    }