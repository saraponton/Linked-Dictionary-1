# Linked Dictionaries
Project by Yuval Cole and Sara Pontón.
## Objectives
- [x] Make the previous project clean and faster.
- [x] Find bottlenecks and look for a new solution to them.
- [x] Use System.currentTimeMillis() to evaluate timing in different parts of your code and try to reduce them.  
- [x] Use the testing theory learned in class to prove the code actually works. 

### Main Changes and New additions
## #1
To improve the time the program took to store the different values in the Linked List we decided to create a Linked list with the same length as the alphabet. 
Inside that Linked List there will be other Linked Lists where where each word will be stored corresponding to the index of that word. 

```java
       public static void createList(){
           for(int i = 0; i < alphabet.length(); i++){
               dict.add (new LinkedList<String>());
           }
       }
```

## #2
Each word will run through this code where it will be stored before the previous word if it's  "smaller" or after if it's "bigger".

```java
       public static void sortFile(File x) {
           try {
               Scanner sc = new Scanner(x);
               int index = 0;
               while (sc.hasNextLine()){
                   String word = sc.nextLine().toLowerCase().replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "");
                   int indicator = alphabet.indexOf(word.charAt(0));
                   int location = compareWord(word, indicator);

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
```

## #3 
We changed our code as we found out it is better to use a Class List Iterator than a for loop as it makes the program run much faster.
```java
 public static int compareWord(String word, int indicator){

           ListIterator iterator = dict.get(indicator).listIterator(0); 
            int index = 0;
           while(iterator.hasNext()) {  

               String word2 = iterator.next().toString();
               if (word.compareTo(word2) < 0){
                   return index ;
               }
               index +=1;

           }
           return -1;

       }
```
## #4 
In this part of the code we used the FileWriter class to write the new document called sorted2.txt. Also, we changed the for loop for a ListIterator to make it faster. Finally, we separate the code so that each word will be printed in a different line. 
```java
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
```

## #5
The program will read each line from both files and store each word in an arraylist. After, it will compare each word to the position they hold in that arraylist. 
```java
 else if (number > -1 && number <= 9457) {
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

                   if (list_file1.get(number).equalsIgnoreCase(list_file2.get(number))) {  
                       System.out.println("Words in position " + number + " match. The word is: " + list_file1.get(number));
                   }
               }
```
## #6
Verifies if the information in both documents its the same.
````java
if(list_file1.equals(list_file2)){
    System.out.println("The files contain the same information ");

}

else{
    System.out.println("Content mismatch in both files");
}

````

## #7
Finally, the program calculates the average of the 3 parameters previously stored. 
````java
public static double doAverage(long a, long b, long c){
        System.out.println("Average time of execution was: " +((a+b+c)/3) +" milliseconds");
        return (a+b+c) / 3;
       }

  
````

