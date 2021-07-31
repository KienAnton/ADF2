package com.t2012e.reflection;

import com.t2012e.entity.Customer;
import com.t2012e.entity.Employee;
import com.t2012e.entity.Student;
import com.t2012e.reflection.myannotation.Column;
import com.t2012e.reflection.myannotation.Id;
import com.t2012e.reflection.myannotation.Table;
import com.t2012e.util.ConnectionHelper;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DemoReflection {
    public static void main(String[] args) throws SQLException {
        migrateData(Customer.class);
        migrateData(Student.class);
        migrateData(Employee.class);
    }

    private static void migrateData(Class clazz) throws SQLException {
        String tableName = clazz.getSimpleName();// default = class name
        if (!clazz.isAnnotationPresent(Table.class)) {
            System.err.println("Class không được đánh dấu để mapping với database.");
            return;
        }
        // Nếu có annotation @table thì lấy tên ra
        Table table = (Table)clazz.getAnnotation(Table.class);
        if (!table.name().isEmpty()) {
            tableName = table.name();
        }
        StringBuilder sqlQueryBuilder = new StringBuilder();
        sqlQueryBuilder.append("CREATE TABLE");
        sqlQueryBuilder.append(" ");
        sqlQueryBuilder.append(tableName);
        sqlQueryBuilder.append(" ");
        sqlQueryBuilder.append("(");
        Field[] fields  = clazz.getDeclaredFields();
        for (Field field: fields) {
            boolean isPrimaryKey = field.isAnnotationPresent(Id.class);
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                sqlQueryBuilder.append(column.fieldName());
                sqlQueryBuilder.append(" ");
                sqlQueryBuilder.append(column.fieldType());
            }else {
                sqlQueryBuilder.append(field.getName());
                sqlQueryBuilder.append(" ");
                // Trong trường hợp không đặt annotation thì bỏ qua hoặc xử lý default
                if (field.getType().getSimpleName().equals("int")) {
                    sqlQueryBuilder.append("INT");
                }else if (field.getType().getSimpleName().equalsIgnoreCase("String")) {
                    sqlQueryBuilder.append("VARCHAR(200)");
                }else if (field.getType().getSimpleName().equalsIgnoreCase("Double")) {
                    sqlQueryBuilder.append("DOUBLE");
                }
            }

            if (isPrimaryKey) {
                Id id = field.getAnnotation(Id.class);
                  if (id.autoIncrement()) {
                    sqlQueryBuilder.append(" AUTO_INCREMENT");
                }
                sqlQueryBuilder.append(" PRIMARY KEY");
            }
            sqlQueryBuilder.append(", ");
        }
        // Xóa dấu , cuối cùng (và khoảng trắng) trước khi thêm dấu ngoặc đơn
        sqlQueryBuilder.setLength(sqlQueryBuilder.length() -2);
        sqlQueryBuilder.append(")");
        System.out.println( sqlQueryBuilder.toString());
        Connection cnn = ConnectionHelper.getConnection();
        Statement stt = cnn.createStatement();
        stt.execute(sqlQueryBuilder.toString());
    }
}
