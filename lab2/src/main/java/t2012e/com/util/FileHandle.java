package t2012e.com.util;

import t2012e.com.entity.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandle {
    public String createFile() {
        String path = null;
        try {
            File myObj = new File("lab2.ser");
            if (myObj.exists()) {
                path = myObj.getName();
            } else {
                myObj.createNewFile();
                path = myObj.getName();
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return path;
    }

    public void writeToFile(String path, ArrayList<Teacher> list) {
        try {
            FileOutputStream writeData = new FileOutputStream(path);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(list);
            writeStream.flush();
            writeStream.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<Teacher> readDataFromFile(String path) {
        try {
            FileInputStream readData = new FileInputStream(path);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            ArrayList<Teacher> listResult = (ArrayList<Teacher>) readStream.readObject();
            readStream.close();
            return listResult;
        } catch (Exception e) {
            return null;
        }
    }
}
