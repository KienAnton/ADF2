package t2012e.com.model;

import t2012e.com.entity.Teacher;
import java.util.ArrayList;

public class TeacherModel  {

    private ArrayList<Teacher> list;
    {
        list = new ArrayList<>();
        list.add(new Teacher("GV001", "Nguyễn Tuân", "06-5-2019",1));
        list.add(new Teacher("GV002", "Đặng Kim Thi", "06-5-2019",2));
        list.add(new Teacher("GV001", "Nguyễn Tuân", "07-5-2019",2));
        list.add(new Teacher("GV003", "Quang Hòa", "07-5-2019",2));
        list.add(new Teacher("GV004", "Văn Thuận", "07-5-2019",1));
        list.add(new Teacher("GV005", "Hồng Luyến", "08-5-2019",5));
        list.add(new Teacher("GV002", "Đặng Kim Thi", "08-5-2019",1));
        list.add(new Teacher("GV002", "Đặng Kim Thi", "09-5-2019",1));
        list.add(new Teacher("GV005", "Hồng Luyến", "09-5-2019",5));
        list.add(new Teacher("GV001", "Nguyễn Tuân", "10-5-2019",1));
        list.add(new Teacher("GV003", "Quang Hòa", "10-5-2019",1));
        list.add(new Teacher("GV004", "Văn Thuận", "11-5-2019",1));
    }

    public ArrayList<Teacher> findAll(){
        return list;
    }
}
