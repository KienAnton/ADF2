package com.t2012e.reflection.generic;

import com.t2012e.reflection.myannotation.Table;
import com.t2012e.util.ConnectionHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MasterModel<T> {
    /**
     * Hàm lưu dữ liệu vào database
     * CreatedAt 29/7/2021
     *
     * @param obj
     */
    public void save(T obj) {
        try {
            Class clazz = obj.getClass();// Lấy thông tin lớp "Class" từ đối tượng.
            //1. Kết nối đến database.
            Connection cnn = ConnectionHelper.getConnection();
            Statement stt = cnn.createStatement();
            String tableName = clazz.getSimpleName();// default = class name
            if (!clazz.isAnnotationPresent(Table.class)) {
                System.err.println("Class không được đánh dấu để mapping với database.");
                return;
            }
            // Nếu có annotation @table thì lấy tên ra
            Table table = (Table) clazz.getAnnotation(Table.class);
            if (!table.name().isEmpty()) {
                tableName = table.name();
            }
            //Sinh ra chuỗi gồm danh sách các trường cần insert vào db
            //vd: (identityNumber, name, balance, email)
            StringBuilder fieldNameBuilder = new StringBuilder();
            fieldNameBuilder.append("(");
            // Lấy ra danh sách các trường
            Field[] fields = clazz.getDeclaredFields();
            System.out.println(fields);
            for (Field value : fields) {
                fieldNameBuilder.append(value.getName());
                fieldNameBuilder.append(", ");
            }
            fieldNameBuilder.setLength(fieldNameBuilder.length() - 2);
            fieldNameBuilder.append(")");
            // Sinh ra chuỗi gồm danh sách các giá trị tương ứng của trường kèm theo việc xử lý nếu kiểu dữ liệu là String thì thêm dấu '
            // Lưu ý cần bổ sung các kiểu dữ liệu khác nhau để sinh ra dấu ' hoặc không có,
            // Ví dụ như: ('A001', 'Kien', 0, 'nguyen@gmail.com')
            StringBuilder fieldValuesBuilder = new StringBuilder();
            fieldValuesBuilder.append("(");
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType().getSimpleName().equals("String")) {
                    fieldValuesBuilder.append("'");
                    fieldValuesBuilder.append(field.get(obj));
                    fieldValuesBuilder.append("', ");
                } else {
                    fieldValuesBuilder.append(field.get(obj));
                    fieldValuesBuilder.append(", ");
                }
            }
            fieldValuesBuilder.setLength(fieldValuesBuilder.length() - 2);
            fieldValuesBuilder.append(")");
            // Tạo ra câu lệnh truy vấn từ các phần tử ở trên kèm theo các lệnh insert và value để tạo ra một câu lệnh như sau:
            // 'insert into Customer (identityNumber, name, balance, email) values ('A001', 'Kien', 0, 'nguyen@gmail.com')
            String sqlQuery = String.format("insert into %s %s values %s", tableName, fieldNameBuilder.toString(), fieldValuesBuilder.toString());
            System.out.println(sqlQuery);
            stt.execute(sqlQuery);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Hàm tìm tất cả dữ liệu trong database
     * * CreatedAt 29/7/2021
     */
    public List<T> findAll() {
        ArrayList<T> result = new ArrayList<>();
        return result;
    }

    /**
     * Hàm update dữ liệu vào database
     * CreatedAt 29/7/2021
     */
    public void delete(T obj) {

    }
}
