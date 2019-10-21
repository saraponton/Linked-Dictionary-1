package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static String path = "/Users/Matheus/Linked/src/com/company/unsorteddict.txt";


    public static void main(String[] args) {
        makeLinkedList();

    }


    public static void makeLinkedList(){
        BufferedReader reader;

        try {

            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while (line != null) {
                System.out.println(line);
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
