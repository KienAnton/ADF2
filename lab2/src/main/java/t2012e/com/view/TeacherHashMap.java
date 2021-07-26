package t2012e.com.view;

import t2012e.com.controller.TeacherController;
import t2012e.com.entity.Teacher;
import t2012e.com.util.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeacherHashMap {
    public TeacherController teacherController = new TeacherController();
    public ArrayList<Teacher> list = teacherController.readFile();
    public HashMap<String, Integer> hashMap1 = new HashMap<>();
    Sort sort = new Sort();
    public HashMap<String, Integer> hashMap(){
        for (int i = 0; i < list.size(); i++) {
           String rollNumber = list.get(i).getRollNumber();
            Integer numberOfCake = list.get(i).getNumberOfCake();
            if (hashMap1.containsKey(rollNumber)) {
                numberOfCake = hashMap1.get(rollNumber) + numberOfCake;
                hashMap1.put(rollNumber, numberOfCake);
            } else {
                hashMap1.put(rollNumber, numberOfCake);
            }
        }
        return hashMap1;
    }

    public void searchByGroup(){
        int iSize = 3;
        int iDem = 0;
        for (Map.Entry<String, Integer> entry : sort.entriesSortedByValues(hashMap1)) { // Đã sắp xếp
            iDem++;
            if (iDem <= iSize) {
                String rollNumber = entry.getKey();
                String fullName = teacherController.findByRollNumber(rollNumber).getFullName();
                int numOfCake = entry.getValue();
                System.out.println(rollNumber + "|" + fullName + "|" + numOfCake);
            }
        }
    }


}
