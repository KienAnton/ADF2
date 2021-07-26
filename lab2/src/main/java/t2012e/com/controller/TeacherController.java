package t2012e.com.controller;

import t2012e.com.entity.Teacher;
import t2012e.com.model.TeacherModel;
import t2012e.com.util.FileHandle;

import java.util.ArrayList;

public class TeacherController {
    private TeacherModel teacherModel = new TeacherModel();
    private ArrayList<Teacher> list = teacherModel.findAll();
    private final FileHandle fileHandle= new FileHandle();
    private final String path = fileHandle.createFile();

    public void writeToFile() {
        fileHandle.writeToFile(path, list);
    }

    public ArrayList<Teacher> readFile() {
        return fileHandle.readDataFromFile(path);
    }

    public Teacher findByRollNumber(String rollNUmber) {
        Teacher teacher = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRollNumber().equals(rollNUmber)){
                teacher = list.get(i);
                break;
            }
        }
        return teacher;
    }
}
