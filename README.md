# Linked Dictionaries

## Objective
- [x] Create a program that takes in a file of words and sort them through Linked Lists.
- [x] Create a new file with the sorted list.
- [x] Take in arguments and return the words corresponding to argument.
- [x] Make it clean and faster.
- [ ] Get a good grade from the project. (waiting for Chelu)

### Structure 
This is supposed to be done as soon as you read the word, it is identified where to be stored and put into the dict automatically.
We approached it with coming up with a linked list of linked lists with different letters of the alphabet so that it would faster the program, since it would only compare with words of the same first letter.
Therefore we create the linked lists:

```java
    static String alphabet="abcdefghijklmnopqrstuvwxyzé";
    static LinkedList<LinkedList> dict = new LinkedList<>();
    [...]
    public static void main(String args[])
    {
        for ( char letter : alphabet.toCharArray()){
            dict.add(new LinkedList<String>());
        }
    [...]
```
Now we have 27 different linked lists for each starter letter in the alphabet.

### File reader
In order to read the file we need to create an object File with the path of the file and a Scanner with the file as argument, after this using *scan.nextLine()* will return the word on the specific line.

```java
            Scanner sc = new Scanner(file);
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
            }
            sc.close();
```

### Sorting 
Since we could *not* use the sorting algorithm for the array we came up with our own known as *getPosition()*, this goes through the word and checks the first letter. After knowing the letter we need to send it to the correct linked list through our int Indicator, which is in int value the representation of which letter it starts. After that we check within the linked list and compare to all words that are smaller than the real word. If thelist is empty it will return -1 and be added randomly.

```java
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
```
### New File
To create a new file we will be using the *PrintWriter* class. This allows us to create a new file and iterate through the list adding one by one the sorted list into the new file. This is the algorithm we came up with:

```java
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
```
We had to create a new array of Strings to substitute the list of lists or else it would be too slow to check for the correct word on the console requirement.

### Console and arguments
In order to grant access to the user through the console to type a number and receive a word corresponding to that number we used a new scanner and gathered until 10 inputs. The input is recorded and checked on the *toPrint* array, and then printed.

```java
            Scanner scan = new Scanner(System.in);
            for (int j = 0;j<10;j++) {
                x = scan.nextInt();
                if (toPrint[x]== null){
                    System.out.println("-1");
                }else {
                    System.out.println(toPrint[x]);
                }

            }
            scan.close();
            
```
## Proof of concept

![proof](https://cdn.discordapp.com/attachments/626181090884845569/637720649309356087/unknown.png)

## Improvements
###
At first we were working with a single linked list with 99 thousand values that would be checked before adding the last values since this was so slow that we didn't even end the program once, we decided to go from 


| One Linked List                   | 60 mins+  |
|-----------------------------------|---|
| Linked list separated by alphabet | 38 mins |
| Linked list for each letter       | 26 mins |
