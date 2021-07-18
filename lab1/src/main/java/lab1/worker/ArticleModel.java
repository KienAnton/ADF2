package lab1.worker;

import lab1.util.ConnectorHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArticleModel {

    public boolean insert(Article obj){
        try {
            //Login và kết nối -> đối tượng của connection.
            Connection cnn = ConnectorHelper.getConnection();
            if(cnn == null){
                throw new SQLException("Can't open connection");
            }
            PreparedStatement prepareStatement = cnn.prepareStatement("insert into articles (url, title, description, content, thumbnail, createdAt, updatedAt, status) values (?, ?, ?, ?, ?, ?, ?, ?)");
            // Tạo ra câu lệnh, đối tượng của lớp
//            String sqlStt = "insert into teachers (identityNumber , fullName, email, address, dob) values ('A001', 'Nguyen', 'kiencuong789@gmail.com', 'Ha Noi', '2016-09-10')";
            prepareStatement.setString(1, obj.getUrl());
            prepareStatement.setString(2, obj.getTitle());
            prepareStatement.setString(3, obj.getDescription());
            prepareStatement.setString(4, obj.getContent());
            prepareStatement.setString(5, obj.getThumbnail());
            prepareStatement.setString(6, obj.getCreatedAtString());
            prepareStatement.setString(7, obj.getUpdateAtString());
            prepareStatement.setInt(8, obj.getStatus());
            prepareStatement.execute();
            return true;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;
    }

}
