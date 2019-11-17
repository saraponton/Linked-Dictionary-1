# Linked Dictionaries
Project by Yuval Cole and Sara Pontón.
## Objectives
- [x] Follow the coding principles.
- [x] Change our code to fit the principles.
- [x] Make the code easier to understand and follow.
- [x] Improve its funcionalities to make it easier and faster.

### Main Coding Principles
## Coding Principle 1: Meaningful names
We changed and improved all the variable names in our code to make them more understandable. This way, you don't need to ask the previous coder or write comments in the code
to explain what each variable stands for. We also used verbs to better explain the meaning of each method. 
Some of the names we changed are: 
- path → unsortedPath
- path2 → alreadySortedPath
- path3 → newSortedPath
- file → unsortedFile
- file2 → alreadySortedFile
- file3 → newSortedFile
- max → maxTime
- createList → createLinkedLists
- compareWord → compareWordinPosition
- writeFile → writeSortedFile
- number → inputNumber


## Coding Principle 2: Keep functions small
The smaller the functions are in your code the better you will be able to understand them. 
Here are a few examples of our short functions: 

```java
      public static void createLinkedLists(){
                for(int i = 0; i < alphabet.length(); i++){
                    dict.add (new LinkedList<String>());
                    //Creates a linked list with the same length of the alphabet. Inside that Linked List there will be other Linked Lists where where each word will be stored corresponding to the index of that word.
                }
            }
```

````java
public static void writeSortedFile() throws IOException { 
           FileWriter fileWriter = new FileWriter("sorted2.txt");

           for(int i = 0; i<alphabet.length(); i++){
               ListIterator iterator = dict.get(i).listIterator(0);

               while(iterator.hasNext()){
                   fileWriter.append(iterator.next().toString());
                   fileWriter.append("\n");
               }
           }

       }
````
## Coding Principle 3: Avoid redundant commenting 
We changed our comments from Spanish to English so that everybody can understand. Moreover, we only left the comments that we 
thought were more relevant to explain the code to someone with less coding knowledge. 

For example :  "In this part of the code we used the FileWriter class to write the new document
                      called sorted2.txt. Also, we changed the for loop for a ListIterator to make it faster. 
                      Finally, we separate the code so that each word will be printed in a different line." 
                         
## Coding Principle 4: Single Responsibility Principle (SRP)




## Coding Principle 5: Don’t Repeat Yourself (DRY)
To avoid repetition, we converted some numbers into int variables to input the variable instead of a number every time. 

For example we created a specific method to count the lines in each file and use that as a variable in our "console" method. 
````java
    public static int countLinesinFile() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(alreadySortedFile));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
````

## Coding Principle 6: Keep your code simple
To keep the functionalities of our code simpler and faster, we made some changes to them.
Some of the methods we added/changed are: countLinesinFile(), verification() and console() method. 
Here is an example of our new verification method: 
````java
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
                count++; 
                System.out.println(count);
                System.out.println(countLinesinFile());
            }
            if(count == countLinesinFile()){
                System.out.println("Both files contain the same words");
                flag = false;
            }
        }
    }
````


## Coding principle 7: YAGNI (You Ain’t Gonna Need It)
We didn't find any method or variables in our code that were unused. We think each class, variable and method
created has the appropriate information for our code to work. 

