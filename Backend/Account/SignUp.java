package Account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {
    public static void insertIntoUser(String username, String domain, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            // Kết nối tới MySQL database, schema 'spotify'
            connection = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/spotify", "root", "Hoang11204");

            if (connection != null) {
                System.out.println("Connected to MySQL database 'Spotify'");

                // Câu lệnh SQL để chèn dữ liệu vào bảng email_info
                String sqlInsert = "INSERT INTO email_info (username, domain, password) VALUES (?, ?, ?)";
                preparedStatement = connection.prepareStatement(sqlInsert);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, domain);
                preparedStatement.setString(3, password);

                // Thực thi câu lệnh
                preparedStatement.executeUpdate();
                System.out.println("Record inserted successfully into email_info");
            }
            else {
                System.out.println("not conne");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkSignUp() {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        // sign up with email
        System.out.println("Sign up with email");
        String Email = sc.nextLine();
        System.out.print("Create your password: ");
        String password = sc.nextLine();

        System.out.println("Tell us about yourself");
        System.out.print("Name: ");
        String name = sc.nextLine();
        
        System.out.print("Date of birth: ");
        int DayOfMonth = sc.nextInt();
        int Month = sc.nextInt();
        int Year = sc.nextInt();

        //format lại dob
        String DateOfBirth = Year + "-" + Month + "-" + DayOfMonth;
        Date BirthDate = Date.valueOf(DateOfBirth);

        if(User.isEmail(Email)){
            String username = Email.split("@")[0];
            String domain = Email.split("@")[1];
            if (!User.checkDuplicate(username, domain)) {
                insertIntoUser(username, domain, password);
                System.out.println("Account created successfully");
            } else {
                System.out.println("Email already linked to an account");
            }
        } else {
            System.out.println("Invalid email");
        }
        sc.close();
    }
}
